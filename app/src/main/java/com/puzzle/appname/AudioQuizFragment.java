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

public class AudioQuizFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_AUDIO_ID = "audio_id";

    public Sounds sounds;
    public SeekBar seekBar;
    private Button buttonRecord, playSoundButton, pause_button;
    private TextView recordLabel;
    private MediaRecorder myAudioRecorder;
    private MediaPlayer play;
    private String outputFile = null;


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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        //sounds = new Sounds(this);
       // super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }


//        seekBar = (SeekBar) getView().findViewById(R.id.seekBar);
//        playSoundButton = (Button) getView().findViewById(R.id.play_button);
//        pause_button = (Button) getView().findViewById(R.id.pauseButton);
//        buttonRecord = (Button) getView().findViewById(R.id.buttonRecord);
//        recordLabel = (TextView) getView().findViewById(R.id.RecordLabel);

//
//        //setContentView(R.layout.activity_content_audio_quiz);
//        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        //setSupportActionBar(toolbar);
//        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
//
//        buttonRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (buttonRecord.getText().toString().equals("Record")) {
//                    try {
//                        recordClicked();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    buttonRecord.setText("Stop Recording");
//                    recordLabel.setText("Recording...");
//
//                } else if (buttonRecord.getText().toString().equals("Stop Recording")) {
//                    stopClicked();
//                    recordLabel.setText("Recording Done");
//                    buttonRecord.setText("Play Recording");
//                } else if (buttonRecord.getText().toString().equals("Play Recording")) {
//                    try {
//                        playClicked();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    //recordLabel.setText("Playing Recording");
//                    buttonRecord.setText("Stop");
//                } else {
//                    stopPlaybackClicked();
//                    buttonRecord.setText("Record");
//                    //recordLabel.setText("");
//
//                }
//            }
//        });
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//        playSoundButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!sounds.mySound.isPlaying()) {
//                    sounds.mySound.start();
//                }
//            }
//        });
//
//        initiateBar();
//
//        pause_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (sounds.mySound.isPlaying()) {
//                    sounds.mySound.stop();
//                }
//            }
//        });
//        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gpp";
 //   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_audio_quiz, container, false);

//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                return false;
//            }
//        });
    seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
    playSoundButton = (Button) getActivity().findViewById(R.id.play_button);
    pause_button = (Button) getActivity().findViewById(R.id.pauseButton);
    buttonRecord = (Button) getActivity().findViewById(R.id.buttonRecord);
    recordLabel = (TextView) getActivity().findViewById(R.id.RecordLabel);

    buttonRecord.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (buttonRecord.getText().toString().equals("Record")) {
                try {
                    recordClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                buttonRecord.setText("Stop Recording");
                recordLabel.setText("Recording...");

            } else if (buttonRecord.getText().toString().equals("Stop Recording")) {
                stopClicked();
                recordLabel.setText("Recording Done");
                buttonRecord.setText("Play Recording");
            } else if (buttonRecord.getText().toString().equals("Play Recording")) {
                try {
                    playClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //recordLabel.setText("Playing Recording");
                buttonRecord.setText("Stop");
            } else {
                stopPlaybackClicked();
                buttonRecord.setText("Record");
                //recordLabel.setText("");

            }
        }
    });

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

    playSoundButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!sounds.mySound.isPlaying()) {
                sounds.mySound.start();
            }
        }
    });

    initiateBar();

    pause_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (sounds.mySound.isPlaying()) {
                sounds.mySound.stop();
            }
        }
    });
    outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gpp";

    return view;

    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }



//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    public void initiateBar() {
        seekBar.setMax(sounds.mySound.getDuration());
        while (sounds.mySound.isPlaying()) {
            seekBar.setProgress(sounds.mySound.getCurrentPosition());
        }
    }

    public void playClicked() throws Exception {

        play = new MediaPlayer();
        play.setDataSource(outputFile);
        play.prepare();
        play.start();

        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer play) {
                play.release(); //release resources
                recordLabel.setText("Well done, try Again");
                buttonRecord.setText("Record");
            }
        });
        recordLabel.setText("Well done, try Again");

    }

    public void stopClicked() {

        myAudioRecorder.stop();
        myAudioRecorder.release();

    }

    public void recordClicked() throws Exception {
        if (myAudioRecorder != null) {
            myAudioRecorder.release();
        }
        File fileOut = new File(outputFile);
        if (fileOut != null) {
            fileOut.delete();
        }
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
        myAudioRecorder.prepare();
        myAudioRecorder.start();
    }

    public void stopPlaybackClicked() {

        if (play != null) {
            play.stop();
            play.release();
            play = null;
        }
    }
}
