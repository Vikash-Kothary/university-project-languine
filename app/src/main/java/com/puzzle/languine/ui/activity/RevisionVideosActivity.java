package com.puzzle.languine.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.Caching;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialRecyclerView;
import com.puzzle.languine.ui.adapter.MenuAdapter;
import com.puzzle.languine.utils.IntentConts;

import java.io.File;
import java.util.ArrayList;

public class RevisionVideosActivity extends MaterialActivity implements MaterialRecyclerView.OnItemClickListener {
    private ArrayList<String> topics;
    private static Caching caching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_videos);
        setupToolbar();
        String module = getIntent().getStringExtra(IntentConts.MODULE_NAME);
        if (caching == null) {
            caching = new Caching();
        }
        topics = new ArrayList<>();
        for (String s : caching.getRevisionVideos(module)) {
            topics.add((new File(s)).getName());
        }

        MenuAdapter adapter = new MenuAdapter(topics);
        new MaterialRecyclerView(this, adapter);

//        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
//        cardList.setHasFixedSize(true);
//
//        //use a linear layout manager
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        cardList.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        String videoNames = getIntent().getStringExtra(Exercises.EXERCISE_VIDEO_NAMES);
//        String[] videoNamesArray = videoNames.split(", ");
//
//        final ArrayList<Lesson> myDataset = new ArrayList<>();
////        for(int i = 2; i < videoNamesArray.length; ++i)
////        {
////            myDataset.add(new Lesson(R.mipmap.ic_launcher,videoNamesArray[i],59));
////        }
//
//
//        for(int i = 2; i < videoNamesArray.length; ++i)
//        {
//            String[] exerciseDetails = videoNamesArray[i].split(";");
//            if(!exerciseDetails[1].equals(" "))
//            {
//                myDataset.add(new Lesson(getResources().getIdentifier(exerciseDetails[1],"drawable",getPackageName()),exerciseDetails[0],0));
//            }
//            else
//            {
//                myDataset.add(new Lesson(R.mipmap.ic_launcher,exerciseDetails[0],0));
//            }
//        }
//
//
//
//
//        LessonAdapter mAdapter = new LessonAdapter(myDataset);
//        cardList.setAdapter(mAdapter);
//        cardList.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Intent intent = new Intent(getBaseContext(), RevisionVideoView.class);
//                        intent.putExtra(TOPIC_TITLE, myDataset.get(position).getLessonName());
//                        intent.putExtra("WhichVideo", position);
//                        intent.putExtra("FirstVideo",getIntent().getIntExtra("FirstVideo",-1));
//                        startActivity(intent);
//                    }
//                })
//        );
    }

    @Override
    public void onItemClick(View view, int position) {
        String moduleName = getIntent().getStringExtra(IntentConts.MODULE_NAME);
        // TODO: pass moduleName into function to return array of topics
        // TODO: use position to get video id from topics array
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra(IntentConts.VIDEO_LINK, caching.getRevisionVideos(moduleName).get(position));
        startActivity(intent);
    }
}
