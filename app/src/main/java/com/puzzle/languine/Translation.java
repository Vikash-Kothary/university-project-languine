package com.puzzle.languine;

/**
 * Created by WSH on 18/02/16.
 */
public class Translation
{
    private String english;
    private String spanish;

    public Translation(String eng, String spa)
    {
        english = eng;
        spanish = spa;
    }

    public String getEnglishWord()
    {
        return english;
    }

    public String getSpanishWord()
    {
        return spanish;
    }
}
