package com.techelevator.controller;

import com.techelevator.dao.QuizQuestionDao;
import com.techelevator.model.QuizQuestionsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class QuizController {

    QuizQuestionDao dao;

    public QuizController(QuizQuestionDao dao){
        this.dao = dao;
    }

    @GetMapping(path = "/categories")
    public List<String> getCategories(){
        return this.dao.getQuizzes();
    }

    @GetMapping(path = "/questions/random")
    public QuizQuestionsDTO getQuizQuestions(@RequestParam(value = "num", defaultValue = "10") int numQuestions){
        List<String> quizCategories = this.dao.getQuizzes();
        String randomCategory = quizCategories.get(new Random().nextInt(quizCategories.size()));

        return new QuizQuestionsDTO(randomCategory, dao.getQuestionsForQuiz(randomCategory, numQuestions));
    }

    /*
     * Partial match categories is supported, e.g.
     * "celeb" with match with "Celebrities"
     */
    @GetMapping(path = "/questions")
    public QuizQuestionsDTO getQuizQuestions(@RequestParam(defaultValue = "general") String category,
                                             @RequestParam(value = "num", defaultValue = "10") int numQuestions)
    {
        return new QuizQuestionsDTO(category, dao.getQuestionsForQuiz(category, numQuestions));
    }
}
