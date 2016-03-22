package com.puzzle.appname.Backend;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class can be used for reading data from assets.
 * <p>
 * Created by Tamara on 05/03/2016.
 */
public class Data {
    private static AssetManager assetManager;
    private static UnitExercise exercise;

    /**
     * Retrieves the data for a given exercise stored in assets
     */
    public static UnitExercise getExercise(String unitNumber, String exerciseName, Context context) {
        try {
            /* replacing á,é,í,ó,ú because of gradle build error
            if file names in assets contain those letters */
            exerciseName = replaceSpanishLetters(exerciseName);

            /*removing spaces and capitalising the first letter of
            each word so it fully matches the naming used for the
            .txt files*/
            exerciseName = removeSpacesAndCapitalise(exerciseName);

            assetManager = context.getAssets(); //without this a NullPointerException occurred when trying to read the file
            InputStream inputStream = assetManager.open("Spanish/exercise-text-files/U0" + unitNumber + exerciseName + ".txt");  //e.g. U01Pronombres.txt
            String text = convertStreamToString(inputStream);   //the text from the .txt file
            //assetManager.close(); //this would cause an error when trying to change activity, though not sure why??
            inputStream.close();

            Log.e("NULL","Exercise name: " + exerciseName);
            setUpExercise(text);    //fill static field 'exercise' with questions

        } catch (IOException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    /**
     * Fills the static field 'exercise' with questions     *
     *
     * @param text contains exercise information
     */
    private static void setUpExercise(String text) {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(text.split(System.getProperty("line.separator"))));

        String spanishDescription = lines.get(0);
        String englishDescription = lines.get(1);
        String typeOfQuiz = lines.get(2);
        int numQuestions = Integer.parseInt(lines.get(3));

        Log.e("NULL", "Spanish line: " + lines.get(0));

        exercise = new UnitExercise(spanishDescription, englishDescription, numQuestions, typeOfQuiz);

        for (int i = 4; i < lines.size(); i++) {
            UnitQuestion question = new UnitQuestion();
            question.setQuestionText(lines.get(i++));
            question.addCorrectAnswers(lines.get(i++));
            question.addPossibleAnswers(lines.get(i));
            exercise.addQuestion(question);
        }
    }

    private static String replaceSpanishLetters(String string) {
        if (string.contains("á")) {
            string = string.replaceAll("á", "a");
        }
        if (string.contains("é")) {
            string = string.replaceAll("é", "e");
        }
        if (string.contains("í")) {
            string = string.replaceAll("í", "i");
        }
        if (string.contains("ó")) {
            string = string.replaceAll("ó", "o");
        }
        if (string.contains("ú")) {
            string = string.replaceAll("ú", "u");
        }
        return string;
    }

    private static String removeSpacesAndCapitalise(String string)
    {
        String[] subWords = string.split(" ");
        String changedWord = subWords[0];

        for(int i = 1; i < subWords.length; ++i)
        {
            subWords[i] = subWords[i].substring(0,1).toUpperCase() + subWords[i].substring(1);
            changedWord += subWords[i];
        }
        return changedWord;
    }

    /**
     * Converts inputStream to String
     *
     * @param inputStream to be converted to String
     * @return String representation of the stream given
     * @throws IOException exception
     */
    private static String convertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sBuild = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sBuild.append(line).append("\n");
        }
        reader.close();
        return sBuild.toString();
    }
}
