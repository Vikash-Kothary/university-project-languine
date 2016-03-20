package com.puzzle.appname.Backend;

import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<String, String> selectedAnswers;  //keeps pair of (correct answer, your answer)

    public UnitExercise(String spanishDescription, String englishDescription,
                        int numQuestions, String type) {
        questionsArr = new ArrayList<>();
        selectedAnswers = new HashMap<>();
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

    public String toString(){
        String result = englishDescription + "\n" + spanishDescription + "\n" +
                "Number of questions: " + numQuestions + "\n" + "Type: " + type;
        for(UnitQuestion q: questionsArr){
            result += q;
        }
        return result;
    }

    public int getQuestionsNumber(){
        return numQuestions;
    }

    public void addPairOfAnswers(String correctAnswer, String selectedAnswer) {
        selectedAnswers.put(correctAnswer, selectedAnswer);
    }

    public HashMap<String, String> getSelectedAnswers() { return selectedAnswers; }


}
