package com.puzzle.appname.Backend;

import java.util.ArrayList;

/**
 * Created by Tamara on 04/03/2016.
 * <p/>
 * Class that represents one question of the quizzes
 */
public class UnitQuestion {
    private ArrayList<String> possibleAnswers, correctAnswers;
    private String questionText;

    public UnitQuestion() {
        possibleAnswers = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }

    public void addPossibleAnswer(String answer) {
        possibleAnswers.add(answer);
    }

    public void addCorrectAnswer(String answer) {
        correctAnswers.add(answer);
    }

    public void setQuestionText(String text) {
        questionText = text;
    }

    public String getQuestionText() {
        return questionText;
    }

    //TODO maybe create another method for checking answers (where more answers are correct)
    //Use this only when there is one possible answer
    public boolean checkAnswer(String answer) {
        if (correctAnswers.contains(answer))
            return true;
        return false;
    }

}
