package com.puzzle.languine.datamodel;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class Module {
    private Bitmap imageBitmap;
    private String videoLink;
    private String moduleName;
    private String moduleDescription;
    private ArrayList<String> revisionVideos = new ArrayList<>();
    private int moduleProgress;

    public Module() {
        init();
    }

    public Module(Bitmap imageBitmap, String moduleName) {
        init();
        this.imageBitmap = imageBitmap;
        this.moduleName = moduleName;
    }

    public Module(String moduleName,Bitmap imageBitmap , int progress) {
        init();
        this.imageBitmap = imageBitmap;
        this.moduleName = moduleName;
        setProgress(progress);
    }

    public Module(String moduleName) {
        this.moduleName = moduleName;
        setProgress(0);
    }

    private void init() {
        this.moduleName = "Module Name";
        this.moduleDescription = "Module Description";
        setProgress(0);
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

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    @Override
    public String toString() {
        return "Module{" +
                ", moduleName='" + moduleName + '\'' +
                ", moduleDescription='" + moduleDescription + '\'' +
                ", moduleProgress=" + moduleProgress +
                '}';
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public ArrayList<String> getRevisionVideos() {
        return revisionVideos;
    }
    public void addRevisionVideos(String url) {
        revisionVideos.add(url);
    }
}
