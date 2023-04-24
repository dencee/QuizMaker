package com.techelevator.services;

import com.techelevator.model.TriviaApi;
import com.techelevator.services.model.TriviaCategories;
import com.techelevator.services.model.TriviaCategory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/*
 * Category #s here: https://opentdb.com/api_config.php
 */
public class RestTriviaService {
    private static final int NUMBER_OF_QUESTIONS = 10;
    private static final String API_URL = "https://opentdb.com/api.php";
    private static final String CATEGORY_URL = "https://opentdb.com/api_category.php";

    private Integer numberOfQuestions, category;
    private String difficulty, type;
    private RestTemplate restTemplate;
    private TriviaCategories categories;

    public RestTriviaService(){
        this.restTemplate = new RestTemplate();
        this.categories = this.getCategories();
    }

    public RestTriviaService(String category){
        this();
        this.difficulty = "medium";
        this.type = "multiple";
        this.numberOfQuestions = NUMBER_OF_QUESTIONS;
        this.category = getCategoryIdByName(category);

        if (this.category == null) {
            this.category = getCategoryIdByName("general");
        }
    }

    public TriviaApi getTrivia() throws RestClientResponseException {
        /*
         * Example:
         * "https://opentdb.com/api.php?amount=10&category=18&difficulty=medium&type=multiple"
         */
        // TODO: Finish
        final String url = API_URL + "?amount=" + this.numberOfQuestions + "&category=" + this.category +
                "&difficulty=" + this.difficulty + "&type=" + this.type;

        TriviaApi triviaData = restTemplate.getForObject(url, TriviaApi.class);

        return triviaData;
    }

    public List<String> getCategoryNames(){
        List<String> categoryNames = new ArrayList<>();

        for(TriviaCategory eachCategory : categories.getCategories() ){
            categoryNames.add(eachCategory.getName());
        }

        return categoryNames;
    }

    private TriviaCategories getCategories(){
        TriviaCategories categories = null;

        try {
            categories = restTemplate.getForObject(CATEGORY_URL, TriviaCategories.class);
        }
        catch(RestClientResponseException e){
            System.out.println(e.getRawStatusCode() + ": " + e.getStatusText());
            e.printStackTrace();
        } catch(ResourceAccessException e){
            System.out.println("ERROR: unable to connect to API.");
            e.printStackTrace();
        }

        return categories;
    }

    private Integer getCategoryIdByName(String category){
        for(TriviaCategory eachCategory : this.categories.getCategories()){
            if(eachCategory.getName().equalsIgnoreCase(category)){
                return eachCategory.getId();
            }
        }

        return null;
    }
}
