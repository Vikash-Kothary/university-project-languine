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
    private Caching caching = new Caching();

    private void init() {
        client = CDAClient.builder()
                .setSpace("mhgakqs7sfkp")
                .setToken("0b24b4b6cbb48e0496edef9d0f3192a527b7f18e91021c66f51dfddac4a52ed6")
                .build();
        tmp = new ArrayList<>();
        items = client.fetch(CDAContentType.class).all().items();
    }

    private CDAContentType getContentType(String queryID) {
        if (items.size() > 0) {
            for (CDAResource res : items) {
                CDAContentType cdaContentType = (CDAContentType) res;
                // Entries count
                CDAArray entries = client.fetch(CDAEntry.class)
                        .where("content_type", cdaContentType.id())
                        .where("limit", "1")
                        .all();

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
        return null;
    }

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

    private void getLanguages() {
        CDAContentType languages = getContentType("languages");
        for (CDAEntry entry : getEntryFromContentType(languages)) {
            System.out.println(entry.getField("languageName"));
            System.out.println(((CDAAsset) entry.getField("languagePicture")).url());
        }
    }

    private void getModuleIntroVideos() {
        final CDAContentType modules = getContentType("modules");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    try {
                        System.out.println(((CDAAsset) entry.getField("moduleIntroVideo")).url());
                        URL url = new URL("http:" + ((CDAAsset) entry.getField("moduleIntroVideo")).url());
                        Caching.storeVideoStream("", entry.getField("moduleName").toString(), url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void getModuleIntroVideosSubtitles() {
        final CDAContentType modules = getContentType("modules");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                        System.out.println(entry.getField("subtitle").toString());

                        Caching.storeSubtitlesStream(entry.getField("subtitle").toString(),entry.getField("moduleName").toString());
                }
            }
        }).start();
    }

    private void getModuleRevisionVideos() {
        final CDAContentType modules = getContentType("modules");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    try {
                        ArrayList<CDAAsset> revisionVideos = entry.getField("revisionVideos");
                        for (int i = 0; i < revisionVideos.size(); i++) {
                            System.out.println((((CDAAsset) revisionVideos.get(i))).url());
                            URL url = new URL("http:" + ((revisionVideos.get(i)).url()));
                            Caching.storeVideoStream("Revision/" + entry.getField("moduleName").toString(), String.valueOf(i), url);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void getModules() {
        final CDAContentType modules = getContentType("modules");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (CDAEntry entry : getEntryFromContentType(modules)) {
                    System.out.println(entry.getField("moduleName"));
                    System.out.println(((CDAAsset) entry.getField("modulePicture")).url());
                    try {
                        URL url = new URL("http:" + ((CDAAsset) entry.getField("modulePicture")).url());
                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        caching.addModule(entry.getField("moduleName").toString(), image, 0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public ContentPull() {
        init();
        getLanguages();
        getModules();
        getModuleIntroVideos();
        getModuleRevisionVideos();
        getModuleIntroVideosSubtitles();
    }

    private static class ContentTypeWrapper {
        private final CDAContentType cdaContentType;
        private final int entries;

        public ContentTypeWrapper(CDAContentType cdaContentType, int entries) {
            this.cdaContentType = cdaContentType;
            this.entries = entries;
        }
    }

}