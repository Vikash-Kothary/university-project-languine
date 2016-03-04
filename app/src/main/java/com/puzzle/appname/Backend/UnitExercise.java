package com.puzzle.appname.Backend;

import java.util.ArrayList;

/**
 * Created by Tamara on 04/03/2016.
 * <p/>
 * Class that represents one exercise of the quizzes
 */
public class UnitExercise {
    private String englishDescription, spanishDescription;
    private int numQuestions;   //number of questions in the exercise
    private ArrayList<UnitQuestion> questionsArr;
    private String type;

    public UnitExercise() {
        questionsArr = new ArrayList<>();
        numQuestions = 0;
    }

    public UnitExercise(String spanishDescription, String englishDescription,
                        int numQuestions, String type) {
        questionsArr = new ArrayList<>();
        this.numQuestions = numQuestions;
        this.type = type;
        this.englishDescription = englishDescription;
        this.spanishDescription = spanishDescription;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpanishDescription(String description) {
        spanishDescription = description;
    }

    public void setEnglishDescription(String description) {
        englishDescription = description;
    }

    public void addQuestion(UnitQuestion question) {
        questionsArr.add(question);
    }

    public void setNumQuestions(String numQuestions) {
        this.numQuestions = Integer.parseInt(numQuestions);
    }

    public ArrayList<UnitQuestion> getAllQuestions() {
        return questionsArr;
    }

    public UnitQuestion getQuestion(int number) {
        return questionsArr.get(number);
    }

    public String getSpanishDescription() {
        return spanishDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }
}
