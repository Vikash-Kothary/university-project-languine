package com.puzzle.appname;

import java.util.ArrayList;

/**
 * Created by fauco on 23/02/2016.
 */
public class GlossaryController
{
    private boolean transMode;
    // false = english to spanish, true = spanish to english

    public GlossaryController()
    {
        transMode = false;
    }

    public ArrayList<String> getTranslations(String glossaryText)
    {
        String[] translationStrings = glossaryText.split(",");
        String[] spanishWords = new String[translationStrings.length];
        String[] englishWords = new String[translationStrings.length];
        ArrayList<Translation> translations = new ArrayList<Translation>();

        for (int i = 0; i < translationStrings.length; ++i)
        {
            String[] twoWords = translationStrings[i].split(": ");
            spanishWords[i] = twoWords[0];
            englishWords[i] = twoWords[1];
            translations.add(new Translation(twoWords[1],twoWords[0]));
        }

        ArrayList<String> trans = new ArrayList<String>();

        for (String s : translationStrings)
        {
            trans.add(s);
        }

        return trans;
    }
}
