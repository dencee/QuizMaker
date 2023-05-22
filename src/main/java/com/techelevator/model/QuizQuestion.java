package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizQuestion {

    @JsonProperty
	private String question;
    @JsonProperty
	private String[] answers;
    @JsonProperty
	private int correctAnswer;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
	public String toString() {
		StringBuilder result = new StringBuilder(question);

		for (int i = 0; i < answers.length; i++) {
			result.append("\n").append(i + 1).append(") ").append(answers[i]);
		}

		return result.toString();
	}
}
