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
    //directory to store images
    private File imageDir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Images");

    /**
     * intitalises the FileNames file and mkdirs makes the directory
     */
    public Caching() {
        imageDir.mkdirs();
        File dir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle");
        fileNameFile = new File(dir, "Names");
    }

    /**
     * adds the modules to the runtime while also storing them to divice storage
     * @param name the name to store the module as
     * @param image the Bitmap image that represents the language
     * @param progress the progress completed by the user
     */
    public void addModule(String name, Bitmap image, int progress) {
        File videoDir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/");
        Module tmp = new Module(name, image, progress);
        //adds the revision video links to the modules
        addVideoLinks(videoDir, name, tmp);

        System.out.println("starting to add");
        boolean update = true;
        boolean updateName = true;
        //adds filenames to the filenames array if it doesn't already have it
        for (int i = 0; i < FileNames.size(); i++) {
            if (FileNames.get(i).equals(name)) {
                updateName = false;
                break;
            }
        }
        //writes the filenames array to the filenames file if a update has been made
        if (updateName) {
            FileNames.add(name);
            writeFileNames();
        }
        //reads the filenames to the filenames file
        getFileNames();

        //check if the modules contains this newly created module
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).toString().equals((new Module(name, image, progress)).toString())) {
                update = false;
                break;
            }
        }
        // adds the module to the module array if it doesn't and stores it to the device for offline usage
        if (update) {
            modules.add(tmp);
            System.out.println("Module added");
            storeModule(tmp);
        }
    }

    /**
     * uses the video links to save the revision video
     * @param directory the directory to store the file revision folder in
     * @param name the name of the revision video file
     * @param tmp a link to the module to add the videoLinks to
     */
    public void addVideoLinks(File directory, String name, Module tmp) {
        tmp.setVideoLink((new File(directory, name + ".mp4").toString()));
        File tmpDir = new File(directory + "/revision/" + name);
        File[] listFiles = tmpDir.listFiles();
        // gets all the folders that contain a revision a video and stores the video link in the module associtated
        if (listFiles != null) {
            for (File f : listFiles) {
                tmp.addRevisionVideos(f.getAbsolutePath());
            }
        }
    }

    /**
     * gets the revision videos links from using the module name
     * @param module the name of the module which the revision videos are for
     * @return returns the arralist of the revsion links
     */
    public ArrayList<String> getRevisionVideos(String module) {
        ArrayList<String> tmp = new ArrayList<>();
        File videoDir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/");
        File tmpDir = new File(videoDir + "/revision/" + module);
        File[] listFiles = tmpDir.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                tmp.add(f.getAbsolutePath());
            }
        }
        return tmp;
    }

    /**
     * stores the module the device
     * @param module the module to store
     */
    public void storeModule(Module module) {
        try {
            System.out.println("really starting to store");
            //file name is module name
            file = new File(imageDir, module.getModuleName() + ".png");
            //don't do anything if the file already exists
            if (!file.exists()) {
                FileOutputStream outputStream;
                outputStream = new FileOutputStream(file);
                //compress "saves" the image to file
                module.getImageBitmap().compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                System.out.println("image saved in " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return the modules array
     */
    public synchronized ArrayList<Module> getEntries() {
        return modules;
    }

    /**
     * gets the module that has that module name
     * @param i index of the the filename from the fileNames file
     * @return the module with that module name
     */
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
                //don't try to open a file that does not exist
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

    /**
     * writes the filenames array to the filenames file
     */
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

    /**
     * get the filenames form the filenames file
     */
    public void getFileNames() {
        try {
            //don't try to open a non-existing file
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
                //initialise the fileames array if the filenames file does not exist
                FileNames = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final int TIMEOUT_CONNECTION = 5000;//5sec
    private static final int TIMEOUT_SOCKET = 30000;//30sec

    /**
     * stores the video from the url
     * @param ex the distinguisher
     * @param moduleName the Module name and later file name
     * @param url url of the video to store
     */
    public synchronized static void storeVideoStream(String ex, String moduleName, URL url) {
        long startTime = System.currentTimeMillis();
        Log.i("tag ", "image download beginning: ");
        File videoDir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/" + ex);
        //creates the directory
        videoDir.mkdirs();
        if (!(new File(videoDir, moduleName + ".mp4")).exists()) {
            try {
                //gets the input stream
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

    /**
     * gets the question from device storage
     * @param name the name of the file to get
     * @return Question class that becomes a question
     */
    public static Question getQuestion(String name) {
        // split the name to use it as the file name and directory location
        String from = name.substring(0, name.indexOf("_"));
        name = name.substring(name.indexOf("_") + 1, name.length());
        File Dir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/Revision/" + from);
        if ((new File(Dir, name)).exists()) {
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(new File(Dir, name));
                ObjectInputStream in;
                in = new ObjectInputStream(inputStream);
                questionFile tmp = (questionFile) in.readObject();
                in.close();
                inputStream.close();
                System.out.println("baker: " + tmp.correctAnswers + "   " + tmp.questionText + "  " + tmp.possibleAnswers);
                return new Question(tmp.correctAnswers, tmp.questionText, tmp.possibleAnswers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * store the question to the device
     * @param question the question class to store
     * @param name the name of the file
     */
    public synchronized static void storeQuestion(Question question, String name) {
        // split the name to use it as the file name and directory location
        String from = name.substring(0, name.indexOf("_"));
        name = name.substring(name.indexOf("_") + 1, name.length());
        File Dir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/Revision/" + from);
        // don't rewite a file a file for no reason
        if (!(new File(Dir, name)).exists()) {
            FileOutputStream outputStream;
            try {
                (new File(Dir.getAbsolutePath())).mkdirs();
                outputStream = new FileOutputStream(new File(Dir, name));
                ObjectOutputStream out;
                out = new ObjectOutputStream(outputStream);
                //make it into a serializable class
                questionFile qf = new questionFile();
                qf.possibleAnswers = question.getPossibleAnswers();
                qf.correctAnswers = question.getCorrectAnswers();
                qf.questionText = question.getQuestionText();
                //write it
                out.writeObject(qf);
                out.flush();
                out.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return the size of the filenames array and file
     */
    public int getFileNameSize() {
        getFileNames();
        System.out.println("FileName size: " + FileNames);
        return FileNames.size();
    }

    /**
     * stores the subtitles in a srt file
     * @param subtitle the subtitles
     * @param name the name to store it as
     */
    public static void storeSubtitlesStream(String subtitle, String name) {
        File Dir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Puzzle/Videos/");
        Dir.mkdirs();
        //Don't recreate a file
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

