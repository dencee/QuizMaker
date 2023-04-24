package com.techelevator.dao;

import com.techelevator.model.QuizQuestion;
import com.techelevator.model.TriviaApi;
import com.techelevator.model.TriviaApiResult;
import com.techelevator.services.RestTriviaService;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApiQuizQuestionDao implements QuizQuestionDao {

    @Override
    public List<String> getQuizzes() {
        return new RestTriviaService().getCategoryNames();
    }

    @Override
    public List<QuizQuestion> getQuestionsForQuiz(String quizName) {

        RestTriviaService api = new RestTriviaService(quizName);
        return mapTriviaApiToQuizQuestions(api.getTrivia());
    }

    private List<QuizQuestion> mapTriviaApiToQuizQuestions(TriviaApi apiData){
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        for(TriviaApiResult questionFromApi : apiData.getResults()){

            List<String> answers = questionFromApi.getIncorrectAnswers();
            int correctAnswerIndex = insertRandomAnswer(answers, questionFromApi.getCorrectAnswer());
            convertHtmlStrings(answers);

            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setQuestion(Jsoup.parse(questionFromApi.getQuestion()).text());
            quizQuestion.setAnswers(answers.toArray(new String[answers.size()]));
            quizQuestion.setCorrectAnswer(correctAnswerIndex + 1);
            quizQuestions.add(quizQuestion);
        }

        return quizQuestions;
    }

    private void convertHtmlStrings(List<String> answers){
        for( int i = 0; i < answers.size(); i++){
            String parsedQuestion = Jsoup.parse(answers.get(i)).text();
            answers.set(i, parsedQuestion);
        }
    }

    private int insertRandomAnswer(List<String> answers, String correctAnswer){
        int randomIndex = new Random().nextInt(answers.size() + 1);
        answers.add(randomIndex, correctAnswer);

        return randomIndex;
    }
}
