package com.puzzle.appname;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;

public class AudioQuizFragment extends MaterialFragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_AUDIO_ID = "audio_id";

    public Sounds sounds;
    public SeekBar seekBar;
    private Button playSoundButton;

    public static AudioQuizFragment newInstance(int audioID) {
        AudioQuizFragment fragment = new AudioQuizFragment();
        Bundle args = new Bundle();
        args.putInt(AudioQuizFragment.ARG_AUDIO_ID, audioID);
        fragment.setArguments(args);
        return fragment;
    }

    public AudioQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_audio_quiz, container, false);
        seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
        playSoundButton = (Button) getActivity().findViewById(R.id.play_button);

        playSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sounds.mySound.isPlaying()) {
                    sounds.mySound.start();
                }
            }
        });

        initiateBar();

        return rootView;
    }

    public void initiateBar()
    {
        seekBar.setMax(sounds.mySound.getDuration());
        while (sounds.mySound.isPlaying())
        {
            seekBar.setProgress(sounds.mySound.getCurrentPosition());
        }
    }
}
