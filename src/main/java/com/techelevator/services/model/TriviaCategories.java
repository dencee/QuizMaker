package com.techelevator.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TriviaCategories {
    @JsonProperty("trivia_categories")
    private TriviaCategory[] categories;

    public TriviaCategory[] getCategories() {
        return categories;
    }

    public void setCategories(TriviaCategory[] categories) {
        this.categories = categories;
    }
}
