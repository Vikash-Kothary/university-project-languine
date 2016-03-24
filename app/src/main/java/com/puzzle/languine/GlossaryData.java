package com.puzzle.languine;

import java.util.ArrayList;

/**
 * Created by fauco on 23/02/2016.
 */
public class GlossaryData
{
    // false = english to spanish, true = spanish to english

    public GlossaryData()
    {

    }

    public ArrayList<Translation> getTranslations(String glossaryText)
    {
        String[] translationStrings = glossaryText.split(", ");
        String[] spanishWords = new String[translationStrings.length];
        String[] englishWords = new String[translationStrings.length];
        ArrayList<Translation> translations = new ArrayList<Translation>();

        for (int i = 0; i < translationStrings.length; ++i)
        {
            String[] twoWords = translationStrings[i].split(": ");
            spanishWords[i] = twoWords[0];
            englishWords[i] = twoWords[1];
            if(i > 0)
            {
                String prevSpanishWord = spanishWords[i-1];
                String prevEnglishWord = englishWords[i-1];

                if(prevSpanishWord.charAt(0) != spanishWords[i].charAt(0))
                {
                    //Adding some space in between and a label to the letter
                    translations.add(new Translation("",""));
                    translations.add(new Translation("", "--- " + spanishWords[i].substring(0, 1).toUpperCase() + " ---"));
                    translations.add(new Translation("",""));
                }
            }
            translations.add(new Translation(twoWords[1],twoWords[0]));
        }

        return translations;
    }
}
