package com.puzzle.languine.datamodel;

import com.puzzle.languine.R;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class Module {
    private int imageID;
    private String moduleName;
    private String moduleDescription;
    private int moduleProgress;

    public Module() {
        init();
    }

    public Module(int imageID, String moduleName) {
        init();
        this.imageID = imageID;
        this.moduleName = moduleName;
    }

    public Module(int imageID, String moduleName, int progress) {
        init();
        this.imageID = imageID;
        this.moduleName = moduleName;
        setProgress(progress);
    }

    public Module(String moduleName) {
        this.moduleName = moduleName;
        setProgress(0);
    }

    private void init() {
        this.imageID = R.mipmap.ic_launcher;
        this.moduleName = "Module Name";
        this.moduleDescription = "Module Description";
        setProgress(0);
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String ModuleName) {
        this.moduleName = ModuleName;
    }

    public int getProgress() {
        return moduleProgress;
    }

    public boolean setProgress(int progress) {
        if (progress > 100 || progress < 0) {
            return false;
        }
        this.moduleProgress = progress;
        return true;
    }
}
