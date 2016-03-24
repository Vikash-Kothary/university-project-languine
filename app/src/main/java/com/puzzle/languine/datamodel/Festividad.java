package com.puzzle.languine.datamodel;

/**
 * Created by Namz on 23/03/2016.
 */
public class Festividad
{
    private int imageID;
    private String date;
    private String spanishName;
    private String englishName;

    public Festividad(int imageID, String date, String spanishName, String englishName)
    {
        this.imageID = imageID;
        this.date = date;
        this.spanishName = spanishName;
        this.englishName = englishName;
    }

    public int getImageID(){
        return imageID;
    }

    public String date() {
        return date;
    }

    public String getSpanishName(){
        return spanishName;
    }

    public String getEnglishName() { return englishName; }
}