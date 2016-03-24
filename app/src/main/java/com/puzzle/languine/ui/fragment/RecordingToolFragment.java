package com.puzzle.languine;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.puzzle.languine.ui.MaterialFragment;

import java.io.File;

/**
 * Created by Namz on 23/03/2016.
 */
public class RecordingToolFragment extends MaterialFragment
{
    private Button buttonRecord;
    private TextView recordLabel;
    private MediaRecorder myAudioRecorder;
    private MediaPlayer play;
    private String outputFile = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_recording_tool, container, false);

        buttonRecord = (Button) findViewById(R.id.buttonRecord);
        recordLabel = (TextView) findViewById(R.id.RecordLabel);

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

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gpp";

        return rootView;
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
                recordLabel.setText("Well done, try again");
                buttonRecord.setText("Record");
            }
        });
        recordLabel.setText("Well done, try again");
    }

    public void stopClicked()
    {

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