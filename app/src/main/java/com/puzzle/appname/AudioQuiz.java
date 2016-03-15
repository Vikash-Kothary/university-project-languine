package com.puzzle.appname;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AudioQuiz extends AppCompatActivity {

    Sounds sounds;
    SeekBar seekBar;


    private Button buttonRecord;
    private TextView recordLabel;
    private MediaRecorder myAudioRecorder;
    private MediaPlayer play;
    private String outputFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sounds = new Sounds(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_audio_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        initiateBar();

        buttonRecord=(Button)findViewById(R.id.buttonRecord);
        recordLabel =(TextView)findViewById(R.id.RecordLabel);
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
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gpp";;

    }


    public void playSound(View view)
    {
        if(!sounds.mySound.isPlaying())
        {
            sounds.mySound.start();
        }
    }

    public void pauseSound(View view)
    {

        sounds.mySound.pause();


    }

    public void initiateBar()
    {
        seekBar.setMax(sounds.mySound.getDuration());
        while(sounds.mySound.isPlaying())
        {
            seekBar.setProgress(sounds.mySound.getCurrentPosition());
        }
    }

    public void playClicked() throws Exception{

        play = new MediaPlayer();
        play.setDataSource(outputFile);
        play.prepare();
        play.start();

//        if (play.isPlaying()) {
//            buttonRecord.setText("Stop");
//        }
//        else{
//            buttonRecord.setText("Record");
//        }

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

    public void stopClicked(){

        myAudioRecorder.stop();
        myAudioRecorder.release();

        //Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show();
    }

    public void recordClicked() throws Exception{
        if (myAudioRecorder != null) {
            myAudioRecorder.release();
        }

        File fileOut = new File(outputFile);

        if(fileOut != null) {
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

    public void stopPlaybackClicked () {
    if(play !=null) {
        play.stop();
        play.release();
        play = null;
    }
    }
}
