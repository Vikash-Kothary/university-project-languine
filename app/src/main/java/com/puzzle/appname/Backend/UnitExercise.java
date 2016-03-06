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

    public UnitExercise(String spanishDescription, String englishDescription,
                        int numQuestions, String type) {
        questionsArr = new ArrayList<>();
        this.numQuestions = numQuestions;
        this.type = type;
        this.englishDescription = englishDescription;
        this.spanishDescription = spanishDescription;
    }

    public void addQuestion(UnitQuestion question) {
        questionsArr.add(question);
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

    public String getType()
    {
        return type;
    }
}
