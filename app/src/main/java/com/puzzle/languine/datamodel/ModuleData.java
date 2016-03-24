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
        for (int i = 0; i < 6; i++) {
            Caching.getQuestion("Greeting_Llamarse"+i);
        }
    }

    public Module getModule(int index){
        return this.get(index);
    }
    public Module getModule(String moduleName){
        for(Module module:this){
            if(module.getModuleName().equals(moduleName))
                return module;
        }
        return null;
    }
}
