package com.puzzle.appname;

/**
 * Created by williamhawken on 12/02/2016.
 */



public class Lesson {
    private int imageID;
    private String lessonName;
    private int progress;

    public Lesson(){}

    public Lesson(int imageID, String lessonName, int progress)
    {
        this.imageID = imageID;
        this.lessonName = lessonName;
        this.progress = progress;
    }

    public int getImageID(){
        return imageID;
    }

    public void setImageID(int imageID)
    {
        this.imageID = imageID;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName)
    {
        this.lessonName = lessonName;
    }

    public int getProgress(){
        return progress;
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
    }
}


