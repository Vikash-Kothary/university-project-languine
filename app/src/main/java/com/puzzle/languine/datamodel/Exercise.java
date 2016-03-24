package com.puzzle.languine.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tamara on 04/03/2016.
 * <p/>
 * Class that represents one exercise of the quizzes
 */
public class Exercise {
    private String englishDescription, spanishDescription, type;
    private int numQuestions, score;   //number of questions in the exercise
    private ArrayList<Question> questionsArr;
    private HashMap<String, String> selectedAnswers;  //keeps pair of (correct answer, your answer)
    private String stringForReview;
    private ArrayList<String> selected;

    public Exercise(String spanishDescription, String englishDescription,
                    int numQuestions, String type) {
        questionsArr = new ArrayList<>();
        selectedAnswers = new HashMap<>();
        this.numQuestions = numQuestions;
        this.type = type;
        this.englishDescription = englishDescription;
        this.spanishDescription = spanishDescription;
        selected = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questionsArr.add(question);
    }

    public ArrayList<Question> getAllQuestions() {
        return questionsArr;
    }

    public Question getQuestion(int number) {
        return questionsArr.get(number);
    }

    public String getSpanishDescription() {
        return spanishDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalPossibleScore() {
        int totalPossibleScore = 0;

        if (!type.equals("multiple")) {
            totalPossibleScore = numQuestions * 10;
        } else {
            totalPossibleScore = 100;
        }
        return totalPossibleScore;
    }

    public String toString() {
        String result = englishDescription + "\n" + spanishDescription + "\n" +
                "Number of questions: " + numQuestions + "\n" + "Type: " + type;
        for (Question q : questionsArr) {
            result += q;
        }
        return result;
    }

    public int getQuestionsNumber() {
        return numQuestions;
    }

    public void addPairOfAnswers(String correctAnswer, String selectedAnswer) {
        selectedAnswers.put(correctAnswer, selectedAnswer);
        selected.add(selectedAnswer);
    }

    public HashMap<String, String> getSelectedAnswers() {
        return selectedAnswers;
    }

    public String getStringForReview() {
        String string = "";
        int questionNum = 1;
        for (Question q : questionsArr) {
            string += questionNum + ". " + q.getQuestionText() + "\n";
            string += "    selected: " + selected.get(questionNum - 1) + "\n";
            string += "    correct: " + q.getCorrectAnswers().get(0) + "\n\n"; //works only for single choice
            questionNum++;
        }
        return string;
    }


}
