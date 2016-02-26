package com.puzzle.appname;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by WSH on 16/02/16.
 */
public class Sounds {

    static MediaPlayer mySound;

    Sounds(Context myContext){
        mySound = MediaPlayer.create(myContext, R.raw.background);
    }

}
