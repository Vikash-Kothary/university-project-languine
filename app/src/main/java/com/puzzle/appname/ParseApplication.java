package com.puzzle.appname;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

/**
 * Created by WSH on 14/03/16.
 */
public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "eAFtucJR0T0NJMexWh7iZWBNi0MBq4OJwSE51TkC", "QErTxyMNBz9nW9I7xpoe2lZu1pi1O3IoxLQdKmVt");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
