package com.puzzle.languine.ui.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;

public class AudioQuizFragment extends MaterialFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private ExerciseData mExercise;

    public MediaPlayer player;
    public SeekBar seekBar;
    private ImageButton playSoundButton;
    private ArrayList<CheckBox> checkBoxes;

    public static AudioQuizFragment newInstance(int audioID, int questionCounter) {
        AudioQuizFragment fragment = new AudioQuizFragment();
        Bundle args = new Bundle();
        args.putInt(IntentConts.AUDIO_ID, audioID);
        fragment.setArguments(args);
        return fragment;
    }

    public AudioQuizFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_audio_quiz, container, false);

        createAudioQuiz();

        return rootView;
    }

    private void createAudioQuiz() {
        seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
        playSoundButton = (ImageButton) getActivity().findViewById(R.id.play_button);
        playSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!player.isPlaying())
                {
                    player.start();
                }
            }
        });

        LinearLayout possibleAnswers = (LinearLayout) findViewById(R.id.possible_answers);

        ArrayList<String> answers = mExercise.getQuestion().getPossibleAnswers();
        checkBoxes = new ArrayList<>();

        for (String answer : answers) {
            CheckBox box = new CheckBox(getContext());
            box.setText(answer);
            possibleAnswers.addView(box);
            checkBoxes.add(box);
        }

        initiateBar();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mExercise = null;
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

    public interface ExerciseData {
        Question getQuestion();

        Exercise getExercise();

        Question getCurrentQuestion();
    }

    public void initiateBar() {
        seekBar.setMax(player.getDuration());
        while (player.isPlaying()) {
            seekBar.setProgress(player.getCurrentPosition());
        }
    }
}
