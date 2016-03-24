package com.puzzle.languine.datamodel;

import android.graphics.drawable.Icon;
import android.media.Image;
import android.widget.ImageButton;

/**
 * Created by fauco on 22/03/2016.
 */
public class Estacion
{
    private int imageID;
    private String name;
    private int month1;
    private int month2;
    private int month3;

    public Estacion(int imageID, String name, int month1, int month2, int month3)
    {
        this.imageID = imageID;
        this.name = name;
        this.month1 = month1;
        this.month2 = month2;
        this.month3 = month3;
    }

    public int getImageID() { return imageID; }

    public String getName() { return name; }

    public int getMonth1() { return month1; }

    public int getMonth2() { return month2; }

    public int getMonth3() { return month3; }
}
