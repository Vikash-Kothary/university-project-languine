package com.puzzle.appname;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by WSH on 16/02/16.
 */
public class Sounds {

    static MediaPlayer mySound;

    private long id;
    private String title;
    private String artist;

    public Sounds(long songID, String songTitle, String songArtist) {
        id=songID;
        title=songTitle;
        artist=songArtist;
    }

    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}

    Sounds(Context myContext)
    {
        Log.e("SOUND","Music file id: "+R.raw.background);
        Log.e("SOUND","Context: "+myContext.getPackageName());
        mySound = MediaPlayer.create(myContext, R.raw.background);
    }
}
