package com.puzzle.languine.datamodel;

import java.util.ArrayList;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class ModuleData extends ArrayList<Module> {

    public ModuleData(){
        Caching caching = new Caching();
        System.out.println("starting Caching");
        for (int position = 0; position < caching.getFileNameSize(); position++) {
            if (caching.getEntries().size() <= position) {
                this.add(caching.getEntry(position));
            } else {
                if (caching.getEntries().get(position) != null) {
                    this.add(caching.getEntries().get(position));
                } else {
                    this.add(caching.getEntry(position));
                }
            }
            System.out.println("Caching: " + caching.getEntry(position));
        }

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
