package com.techelevator.model;

import java.util.List;

public class QuizQuestionsDTO {
    String category;
    Integer numQuestions;
    List<QuizQuestion> questions;

    public QuizQuestionsDTO(String category, List<QuizQuestion> questions) {
        this.category = category;
        this.questions = questions;
        this.numQuestions = questions.size();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public Integer getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }
}
