package com.puzzle.languine.datamodel;

/**
 * Created by fauco on 23/03/2016.
 */
public class Hour
{
    private int imageID;
    private String time;

    public Hour(int imageID, String time)
    {
        this.imageID = imageID;
        this.time = time;
    }

    public int getImageID() { return imageID; }

    public String getTime() { return time; }
}
