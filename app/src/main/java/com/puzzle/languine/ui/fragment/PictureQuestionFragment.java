package com.puzzle.languine.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.utils.IntentConts;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PictureQuestionFragment extends MaterialFragment
{
    private ExerciseData mExercise;
    private Exercise exercise;
    private ImageView[] images = new ImageView[6];

    public static PictureQuestionFragment newInstance(int questionCounter) {
        PictureQuestionFragment fragment = new PictureQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(IntentConts.QUESTION_COUNTER, questionCounter);
        fragment.setArguments(args);
        return fragment;
    }

    public PictureQuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_picture_question, container, false);

        createPicturesQuiz();

        return rootView;
    }

    private void createPicturesQuiz()
    {
        TextView questionText = (TextView) findViewById(R.id.picture_question);
        questionText.setText(mExercise.getQuestion().getQuestionText());
        images[0] = (ImageView) findViewById(R.id.picture1);
        images[1] = (ImageView) findViewById(R.id.picture2);
        images[2] = (ImageView) findViewById(R.id.picture3);
        images[3] = (ImageView) findViewById(R.id.picture4);
        images[4] = (ImageView) findViewById(R.id.picture5);
        images[5] = (ImageView) findViewById(R.id.picture6);

        exercise = mExercise.getExercise();

        ArrayList<String> answers = mExercise.getQuestion().getPossibleAnswers();

        for(int i = 0; i < answers.size(); ++i)
        {
            AssetManager assetManager = getAssets();
            InputStream istr = null;
            try
            {
                istr = assetManager.open("Spanish/pictures-for-exercises/"+answers.get(i)+".png");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            images[i].setImageBitmap(bitmap);

            if(exercise.getSelectedAnswers().size() > getArguments().getInt(IntentConts.QUESTION_COUNTER)
                    && !exercise.getSelectedAnswers().isEmpty())
            {
                images[i].clearColorFilter();
                if (exercise.getSelectedAnswers().get(mExercise.getCurrentQuestion().getCorrectAnswers().get(0)).equals(answers.get(i)))
                {
                    images[i].setColorFilter(Color.argb(70, 31, 190, 214));
                }
                images[i].setEnabled(false);
            }
            else
            {
                images[i].setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v)
                    {
                        if(mExercise.getSelectedImage() == null || !mExercise.getSelectedImage().equals(v))
                        {
                            for(ImageView image : images)
                            {
                                image.clearColorFilter();
                            }
                            //mExercise.getSelectedImage() = (ImageView) v;
                            ((ImageView) v).setColorFilter(Color.argb(70,31,190,214));
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ExerciseData) {
            mExercise = (ExerciseData) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mExercise = null;
    }

    public interface ExerciseData {
        Question getQuestion();
        Exercise getExercise();
        Question getCurrentQuestion();
        ImageView getSelectedImage();
    }
}
