package com.puzzle.languine.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.TODO.Data;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.activity.ResultActivity;
import com.puzzle.languine.utils.IntentConts;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PictureQuestionFragment extends MaterialFragment
{
    private String lessonNumber, exerciseName, quizType, selectedAnswer, moduleName;
    private Exercise unitExercise = null;
    private Question currentQuestion;
    private int questionCounter = 0;
    private ImageButton nextButton, previousButton;
    private RadioGroup possibleAnswers;
    private ImageView selectedImage;
    private ImageView[] images = new ImageView[6];

    public PictureQuestionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_picture_question, container, false);

        exerciseName = getActivity().getIntent().getStringExtra(IntentConts.EXERCISE_NAME);
        moduleName = getActivity().getIntent().getStringExtra(IntentConts.MODULE_NAME);
        lessonNumber = getActivity().getIntent().getStringExtra(IntentConts.LESSON_NUMBER);

        unitExercise = Data.getExercise(lessonNumber, exerciseName, getContext());
        currentQuestion = unitExercise.getQuestion(questionCounter);
        previousButton = (ImageButton) findViewById(R.id.previous_question);
        nextButton = (ImageButton) findViewById(R.id.next_question);

        createPicturesQuiz();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion = unitExercise.getQuestion(questionCounter);

                if (selectedImage != null) {
                    getSelection();
                }

                String[] resultMessage = getResultStrings();
                if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());

                    builder.setMessage(resultMessage[0]).setTitle(resultMessage[1]);

                    if(questionCounter < unitExercise.getQuestionsNumber() - 1) {
                        builder.setPositiveButton("CONTINUE", null);
                    }
                    else
                    {
                        builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), ResultActivity.class);
                                intent.putExtra(IntentConts.QUIZ_SCORE,""+unitExercise.getScore());
                                intent.putExtra(IntentConts.MAX_SCORE,""+unitExercise.getTotalPossibleScore());
                                startActivity(intent);
                            }
                        });
                    }

                    android.support.v7.app.AlertDialog dialog = builder.create();
                    dialog.show();
                }

                if (!resultMessage[0].equals("Please select an answer.")) {
                    if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
                        unitExercise.addPairOfAnswers(currentQuestion.getCorrectAnswers().get(0), selectedAnswer);
                    }
                    selectedImage.clearColorFilter();
                    if (questionCounter < unitExercise.getQuestionsNumber() - 1) {
                        ++questionCounter;
                        currentQuestion = unitExercise.getQuestion(questionCounter);
                        createPicturesQuiz();
                    }
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --questionCounter;
                currentQuestion = unitExercise.getQuestion(questionCounter);
                createPicturesQuiz();
            }
        });

        return rootView;
    }

    private void createPicturesQuiz() {
        TextView questionText = (TextView) findViewById(R.id.picture_question);
        questionText.setText(unitExercise.getQuestion(questionCounter).getQuestionText());
        images[0] = (ImageView) findViewById(R.id.picture1);
        images[1] = (ImageView) findViewById(R.id.picture2);
        images[2] = (ImageView) findViewById(R.id.picture3);
        images[3] = (ImageView) findViewById(R.id.picture4);
        images[4] = (ImageView) findViewById(R.id.picture5);
        images[5] = (ImageView) findViewById(R.id.picture6);

        ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

        for (int i = 0; i < answers.size(); ++i) {
            AssetManager assetManager = getAssets();
            InputStream istr = null;
            try {
                istr = assetManager.open("Spanish/pictures-for-exercises/" + answers.get(i) + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            images[i].setImageBitmap(bitmap);

            if (unitExercise.getSelectedAnswers().size() > questionCounter && !unitExercise.getSelectedAnswers().isEmpty()) {
                images[i].clearColorFilter();
                if (unitExercise.getSelectedAnswers().get(currentQuestion.getCorrectAnswers().get(0)).equals(answers.get(i))) {
                    images[i].setColorFilter(Color.argb(70, 31, 190, 214));
                }
                images[i].setEnabled(false);
            } else {
                images[i].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (selectedImage == null || !selectedImage.equals(v)) {
                            for (ImageView image : images) {
                                image.clearColorFilter();
                            }
                            selectedImage = (ImageView) v;
                            ((ImageView) v).setColorFilter(Color.argb(70, 31, 190, 214));
                        }
                    }
                });
            }
        }
        if (questionCounter == 0) {
            previousButton.setVisibility(View.INVISIBLE);
        }
        else {
            previousButton.setVisibility(View.VISIBLE);
        }
        if (questionCounter == unitExercise.getQuestionsNumber() - 1) {
            nextButton = (ImageButton) findViewById(R.id.next_question);
            nextButton.setImageResource(R.drawable.finish);
        }
    }

    private void getSelection() {
        for (int i = 0; i < images.length; ++i) {
            if (selectedImage.equals(images[i])) {
                selectedAnswer = currentQuestion.getPossibleAnswers().get(i);
            }
        }
    }

    private String[] getResultStrings() {
        String resultMessage = "";
        String resultTitle = "";

        if (selectedImage == null)
        {
            resultMessage = "Please select an answer.";
        }
        else if (currentQuestion.checkAnswer(selectedAnswer))
        {
            resultMessage = "That's right! You selected the correct response.";
            resultTitle = "Correct";
            unitExercise.setScore(unitExercise.getScore() + 10);
        }
        else
        {
            resultMessage = "You did not select the correct response.";
            resultTitle = "Incorrect";
        }

        String[] resultStrings = {resultMessage, resultTitle};

        return resultStrings;
    }
}
