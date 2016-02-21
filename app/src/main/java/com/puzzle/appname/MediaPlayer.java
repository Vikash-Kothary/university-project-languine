package com.puzzle.appname;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController.MediaPlayerControl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.ListView;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import com.puzzle.appname.MusicService.MusicBinder;


public class MediaPlayer extends Activity implements MediaPlayerControl
{
    private MusicController controller;
    private ArrayList<Sounds> songList;
    private ListView songView;
    private MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button shuffle = (Button) findViewById(R.id.shuffle);
        Button stop = (Button) findViewById(R.id.stop);
        SeekBar duration = (SeekBar) findViewById(R.id.seekBar);

        setController();

    }

    /*private void playClip(View view)
    {
        Sounds.mySound.start();
    }

    private */

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBinder binder = (MusicBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list
            //musicSrv.setList(songList);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }


    private void setController() {
        controller = new MusicController(this);

        controller.setPrevNextListeners(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                playNext;
            }
        }, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                playPrev;
            }
        });


        controller.setMediaPlayer(this);
//        controller.setAnchorView(findViewById(R.id.song_list));
    }



    public void songPicked(View view){
        musicSrv.setSong(Integer.parseInt(view.getTag().toString()));
        musicSrv.playSong();
    }

    @Override
    public void start() {
//        MusicService.go();
    }

    @Override
    public void pause() {
//        MusicService.pausePlayer;
    }


    @Override
    public void seekTo(int pos) {
//        MusicService.seekTo(pos);
    }

    @Override
    public int getDuration() {

//        if(musicSrv!=null &amp;&amp; musicBound &amp;&amp; musicSrv.isPng())
//        return musicSrv.getDur();
//        else
        return 0;
    }

    @Override
    public int getCurrentPosition() {

//        if(MusicService!=null &amp;&amp; musicBound &amp;&amp; MusicService.isPng())
//        return MusicService.getPosn();
//        else
        return 0;
    }



    @Override
    public boolean isPlaying() {

//        if(MusicService!=null &amp;&amp; musicBound)
//        return MusicService.isPng();
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    //play next

    private void playNext(){
        //MusicService.playNext();
        controller.show(0);
    }

    private void playPrev(){
        //MusicService.playPrev();
        controller.show(0);
    }

    public interface OnPreparedListener {
    }
}
