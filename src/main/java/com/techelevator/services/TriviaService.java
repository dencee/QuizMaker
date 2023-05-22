package com.techelevator.services;

import com.techelevator.model.TriviaApi;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

public interface TriviaService {

    List<String> getCategoryNames();
    TriviaApi getTrivia() throws RestClientResponseException;
}
