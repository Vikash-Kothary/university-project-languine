package com.puzzle.languine;

/**
 * Created by WSH on 16/02/16.
 */



import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.View;


public class SmallSounds {

    static SoundPool sp;
    SoundPool.Builder builder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributeBuilder;

   // static int captureId;


    SmallSounds(Context myContext){
        attributeBuilder = new AudioAttributes.Builder();
        attributeBuilder.setUsage(AudioAttributes.USAGE_GAME);
        attributeBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
        attributes = attributeBuilder.build();

        builder = new SoundPool.Builder();
        builder.setAudioAttributes(attributes);
        sp = builder.build();

        //captureId = sp.load(myContext, R.raw.capture, 1);


    }


}
