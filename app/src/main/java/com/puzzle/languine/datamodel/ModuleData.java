package com.puzzle.languine.datamodel;

import java.util.ArrayList;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class ModuleData extends ArrayList<Module> {

    public ModuleData(){
        this.add(new Module("Greetings"));
        this.add(new Module("Checking in"));
        this.add(new Module("Sightseeing"));
        this.add(new Module("Directions"));
        this.add(new Module("Eating"));
        this.add(new Module("Likes"));
        this.add(new Module("Planning"));
        this.add(new Module("Shopping"));
        this.add(new Module("Dating"));
        this.add(new Module("Swearing"));

//        this.add(new Module(R.drawable.greetings, "Greetings"));
//        this.add(new Module(R.drawable.checkingin, "Checking in"));
//        this.add(new Module(R.drawable.sightseeing, "Sightseeing"));
//        this.add(new Module(R.drawable.directions, "Directions"));
//        this.add(new Module(R.drawable.eating, "Eating"));
//        this.add(new Module(R.drawable.likes, "Likes"));
//        this.add(new Module(R.drawable.planning, "Planning"));
//        this.add(new Module(R.drawable.shopping, "Shopping"));
//        this.add(new Module(R.drawable.dating, "Dating"));
//        this.add(new Module(R.drawable.greetings, "Swearing"));
    }

    public Module getModule(int index){
        return this.get(index);
    }
}
