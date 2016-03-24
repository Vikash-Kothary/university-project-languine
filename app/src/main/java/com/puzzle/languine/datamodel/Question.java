package com.puzzle.languine.datamodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tamara on 04/03/2016.
 * <p/>
 * Class that represents one question of the quizzes
 */
public class Question {
    private ArrayList<String> possibleAnswers, correctAnswers;
    private String questionText;

    public Question() {
        possibleAnswers = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }

    public void addPossibleAnswers(String answers) {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(answers.split(",")));

        //goes through the possible answers, if one has a ;, replace it with a ,
        for(int i = 0; i < lines.size(); ++i)
        {
            String line = lines.get(i);
            if(line.contains(";"))
            {
                line = line.replace(";",",");
                lines.remove(i);
                lines.add(i,line);
            }
        }
        possibleAnswers = lines;
    }

    public void addCorrectAnswers(String answers) {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(answers.split(",")));
        correctAnswers = lines;
    }

    public void setQuestionText(String text) {
        questionText = text;
    }

    public String getQuestionText()
    {
        String questionText = this.questionText;
        if(questionText.contains("_"))
        {
            questionText = questionText.replace("_","_______");
        }
        return questionText;
    }

    //TODO maybe create another method for checking answers (where more answers are correct)
    //Use this only when there is one possible answer
    public boolean checkAnswer(String answer)
    {
        if(answer.contains(","))
        {

        }
        else
        {
            if (correctAnswers.contains(answer))
                return true;
        }
        return false;
    }

    public String toString(){
        String result = questionText + "\n";
        for(String answer: possibleAnswers){
            result += answer + "\n";
        }
        return result;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public ArrayList<String> getCorrectAnswers() { return correctAnswers; }
}
