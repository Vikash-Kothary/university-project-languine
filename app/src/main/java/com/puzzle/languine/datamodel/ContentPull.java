package com.puzzle.languine.datamodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAContentType;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.contentful.java.cda.FetchQuery;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ContentPull {
    private CDAClient client;
    private ArrayList<ContentTypeWrapper> tmp;
    private List<CDAResource> items;
    // the file used to store things "Cache" them
    private Caching caching = new Caching();

    /**
     * initialises the client with the ascces token and space to explore in
     */
    private void init() {
        client = CDAClient.builder()
                .setSpace("mhgakqs7sfkp")
                .setToken("0b24b4b6cbb48e0496edef9d0f3192a527b7f18e91021c66f51dfddac4a52ed6")
                .build();
        tmp = new ArrayList<>();
        //fetch all content types from the content management system
        items = client.fetch(CDAContentType.class).all().items();
    }

    /**
     * get the type of content for a given id, from the web
     *
     * @param queryID the id of the content type to get
     * @return content type in cda form
     */
    private CDAContentType getContentType(String queryID) {
        if (items.size() > 0) {
            for (CDAResource res : items) {
                CDAContentType cdaContentType = (CDAContentType) res;
                // Entries count
                CDAArray entries = client.fetch(CDAEntry.class)
                        .where("content_type", cdaContentType.id())
                        .where("limit", "1")
                        .all();
                //uses a wrapper to protect the entries and not allow random data to be poured in
                ContentTypeWrapper ct = new ContentTypeWrapper(cdaContentType, entries.total());
                tmp.add(ct);
            }
        }
        // Logs Content Types
        for (ContentTypeWrapper a : tmp) {
            if (a.cdaContentType.id().equals(queryID)) {
                return a.cdaContentType;
            }
        }
        //if there is no matching content type to the id then return null
        return null;
    }

    /**
     * uses the content type to reture a list of entries that match that content type, from the web
     *
     * @param contentType the content type you want
     * @return ArrayList<CDAEntry> the entries that match
     */
    private ArrayList<CDAEntry> getEntryFromContentType(CDAContentType contentType) {
        FetchQuery<CDAEntry> query = client.fetch(CDAEntry.class).where("content_type", contentType.id());
        List<CDAResource> results = new ArrayList<>();
        results.addAll(query.all().entries().values());
        ArrayList<CDAEntry> entries = new ArrayList<>();
        for (CDAResource r : results) {
            if (r instanceof CDAEntry) {
                entries.add((CDAEntry) r);
            }
        }
        return entries;
    }

    /**
     * get the languages,from the web, that match the content type
     */
    private void getLanguages() {
        CDAContentType languages = getContentType("languages");
        for (CDAEntry entry : getEntryFromContentType(languages)) {
            //code to handle them goes here
            System.out.println(entry.getField("languageName"));
            System.out.println(((CDAAsset) entry.getField("languagePicture")).url());
        }
    }

    /**
     * get the videos,from the web, that introduce the module
     */
    private void getModuleIntroVideos() {
        final CDAContentType modules = getContentType("modules");
        //reading and writing to files cannot be on main thread hence the new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    try {
                        System.out.println(((CDAAsset) entry.getField("moduleIntroVideo")).url());
                        //gets the url of the file
                        URL url = new URL("http:" + ((CDAAsset) entry.getField("moduleIntroVideo")).url());
                        //stores it as a video with the module name being the file name
                        Caching.storeVideoStream("", entry.getField("moduleName").toString(), url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * gets the subtitles,from the web, to the videos that introduce the module
     */
    private void getModuleIntroVideosSubtitles() {
        final CDAContentType modules = getContentType("modules");
        //reading and writing to files cannot be on main thread hence the new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    System.out.println(entry.getField("subtitle").toString());
                    //stores it with module name being the file name
                    Caching.storeSubtitlesStream(entry.getField("subtitle").toString(), entry.getField("moduleName").toString());
                }
            }
        }).start();
    }

    /**
     * gets the test questions and from the web
     */
    static int k = 0;
    private synchronized void getTestQuestions() {
        final CDAContentType modules = getContentType("textQuestions");
        //reading and writing to files cannot be on main thread hence the new thread
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    System.out.println("baker: " + entry.getField("module").toString() + ": " + entry.getField("question"));
                    //adds the entries to a Question class
                    Question q = new Question();
                    q.setQuestionText(entry.getField("question").toString());
                    ArrayList<String> array = entry.getField("correctAnswer");
                    for (int i = 0; i < array.size(); i++) {
                        q.addCorrectAnswers(array.get(i));
                    }
                    array = entry.getField("allPossibleAnswers");
                    for (int i = 0; i < array.size(); i++) {
                        q.addPossibleAnswers(array.get(i));
                    }
                    /**
                     * the Question class is stored
                     */
                    Caching.storeQuestion(q, entry.getField("module").toString() + k);
                    ++k;
                }
            }
        }).start();
    }

    /**
     * gets the revision videos for modules from the web
     */
    private void getModuleRevisionVideos() {
        final CDAContentType modules = getContentType("modules");
        //reading and writing to files cannot be on main thread hence the new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    try {
                    //gets all the revsionvideo url links for a module
                        ArrayList<CDAAsset> revisionVideos = entry.getField("revisionVideos");
                        for (int i = 0; i < revisionVideos.size(); i++) {
                            System.out.println((((CDAAsset) revisionVideos.get(i))).url());
                            URL url = new URL("http:" + ((revisionVideos.get(i)).url()));
                            //stores it as a video with the revision video title as the file name
                            Caching.storeVideoStream("Revision/" + entry.getField("moduleName").toString(), (revisionVideos.get(i)).title(), url);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * gets all the modules and images stored on the web
     */
    private void getModules() {
        final CDAContentType modules = getContentType("modules");
        //reading and writing to files cannot be on main thread hence the new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    System.out.println(entry.getField("moduleName"));
                    System.out.println(((CDAAsset) entry.getField("modulePicture")).url());
                    try {
                        //gets the bitmap from the module picture url
                        URL url = new URL("http:" + ((CDAAsset) entry.getField("modulePicture")).url());
                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        //stores the bitmap with the file name as the module name
                        caching.addModule(entry.getField("moduleName").toString(), image, 0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * runs through all the methods
     */
    public ContentPull() {
        init();
        getLanguages();
        getModules();
        getModuleIntroVideos();
        getModuleRevisionVideos();
        getModuleIntroVideosSubtitles();
        getTestQuestions();
    }

    /**
     * filter for content type and entries
     */
    private static class ContentTypeWrapper {
        private final CDAContentType cdaContentType;
        private final int entries;

        public ContentTypeWrapper(CDAContentType cdaContentType, int entries) {
            this.cdaContentType = cdaContentType;
            this.entries = entries;
        }
    }

}