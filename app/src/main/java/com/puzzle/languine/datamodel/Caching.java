package com.puzzle.languine.datamodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Caching {
    private File file;
    private File fileNameFile;

    private static ArrayList<Module> modules = new ArrayList<>();
    private static ArrayList<String> FileNames = new ArrayList<>();
    private File imageDir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Images");


    public Caching() {
        imageDir.mkdirs();
        File dir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle");
        fileNameFile = new File(dir, "Names");
    }


    public void addModule(String name, Bitmap image, int progress) {
        File videoDir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/");
        Module tmp = new Module(name, image, progress);
        addVideoLinks(videoDir, name, tmp);

        System.out.println("starting to add");
        boolean update = true;
        boolean updateName = true;
        for (int i = 0; i < FileNames.size(); i++) {
            if (FileNames.get(i).equals(name)) {
                updateName = false;
                break;
            }
        }
        if (updateName) {
            FileNames.add(name);
            writeFileNames();
        }

        getFileNames();

        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).toString().equals((new Module(name, image, progress)).toString())) {
                update = false;
                break;
            }
        }

        if (update) {
            modules.add(tmp);
            System.out.println("Module added");
            storeModule(tmp);
        }
    }

    public void addVideoLinks(File directory, String name, Module tmp) {
        tmp.setVideoLink((new File(directory, name + ".mp4").toString()));
        File tmpDir = new File(directory + "/revision/" + name);
        File[] listFiles = tmpDir.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                tmp.addRevisionVideos(f.getAbsolutePath());
            }
        }
    }

    public void storeModule(Module module) {
        try {
            System.out.println("really starting to store");
            //file name is module name
            file = new File(imageDir, module.getModuleName() + ".png");
            if (!file.exists()) {
                FileOutputStream outputStream;
                outputStream = new FileOutputStream(file);
                //saves the image to file
                module.getImageBitmap().compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                System.out.println("image saved in " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized ArrayList<Module> getEntries() {
        return modules;
    }

    public synchronized Module getEntry(int i) {
        try {
            System.out.println("Modules: " + modules + "  " + modules.size());
            if (FileNames == null || getFileNameSize() <= 0) {
                System.out.println("getting file names");
                getFileNames();
            }
            if (FileNames.size() >= 1) {
                file = new File(imageDir, FileNames.get(i) + ".png");
                System.out.println("testing file: " + file.getAbsolutePath());
                if (file.exists()) {
                    //recover from files on system and put in modules and filename
                    FileInputStream inputStream = new FileInputStream(file);
                    addModule(FileNames.get(i), BitmapFactory.decodeStream(inputStream), 0);
                    inputStream.close();
                    return modules.get(i);
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeFileNames() {
        try {
            FileOutputStream outputStream;
            outputStream = new FileOutputStream(fileNameFile);
            ObjectOutputStream out;
            out = new ObjectOutputStream(outputStream);
            out.writeObject(FileNames);
            out.flush();
            out.close();
            outputStream.close();
            System.out.println("FileNames written to files");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFileNames() {
        try {
            if (!fileNameFile.createNewFile()) {
                FileInputStream inputStream = new FileInputStream(fileNameFile);
                if (inputStream.getChannel().size() > 0) {
                    ObjectInputStream in = new ObjectInputStream(inputStream);
                    try {
                        FileNames = (ArrayList<String>) in.readObject();
                        System.out.println("FileNames Recieved:" + FileNames);
                        in.close();
                        inputStream.close();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                FileNames = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int TIMEOUT_CONNECTION = 5000;//5sec
    private static final int TIMEOUT_SOCKET = 30000;//30sec

    public synchronized static void storeVideoStream(String ex, String moduleName, URL url) {
        long startTime = System.currentTimeMillis();
        Log.i("tag ", "image download beginning: ");
        File videoDir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/" + ex);
        videoDir.mkdirs();
        if (!(new File(videoDir, moduleName + ".mp4")).exists()) {
            try {
                URLConnection ucon = url.openConnection();

                //this timeout affects how long it takes for the app to realize there's a connection problem
                ucon.setReadTimeout(TIMEOUT_CONNECTION);
                ucon.setConnectTimeout(TIMEOUT_SOCKET);

                //Define InputStreams to read from the URLConnection.
                // uses 3KB download buffer
                InputStream is = ucon.getInputStream();
                BufferedInputStream inStream = new BufferedInputStream(is, 1024 * 5);
                FileOutputStream outStream = new FileOutputStream(new File(videoDir, moduleName + ".mp4"));
                byte[] buff = new byte[5 * 1024];

                //Read bytes (and store them) until there is nothing more to read(-1)
                int len;
                while ((len = inStream.read(buff)) != -1) {
                    outStream.write(buff, 0, len);
                }

                //clean up
                outStream.flush();
                outStream.close();
                inStream.close();
                Log.i("tag ", "download completed in "
                        + ((System.currentTimeMillis() - startTime) / 1000)
                        + " sec");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public int getFileNameSize() {
        getFileNames();
        System.out.println("FileName size: " + FileNames);
        return FileNames.size();
    }

    public static void storeSubtitlesStream(String subtitle, String name) {
        File Dir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/");
        Dir.mkdirs();
        if (!(new File(Dir, name + ".srt")).exists()) {
            try {
                OutputStream out = new FileOutputStream(new File(Dir, name + ".srt"));
                OutputStreamWriter writer = new OutputStreamWriter(out);
                writer.write(subtitle, 0, subtitle.length());
                writer.flush();
                writer.close();
                out.close();
                System.out.println("saved subtitle");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

