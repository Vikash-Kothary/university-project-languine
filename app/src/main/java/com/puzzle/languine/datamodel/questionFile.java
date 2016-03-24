package com.puzzle.languine.datamodel;

import java.io.Serializable;
import java.util.ArrayList;

public class questionFile implements Serializable{
    private static final long serialVersionUID = 446; //Added serialVersionUID variable
    public ArrayList<String> possibleAnswers, correctAnswers;
    public String questionText;
}
