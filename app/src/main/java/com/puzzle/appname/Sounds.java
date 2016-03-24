package com.puzzle.appname;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by WSH on 16/02/16.
 */
public class Sounds {

    public static MediaPlayer mySound;

    Sounds(Context myContext, int music)
    {
        mySound = MediaPlayer.create(myContext, music);
    }
}
