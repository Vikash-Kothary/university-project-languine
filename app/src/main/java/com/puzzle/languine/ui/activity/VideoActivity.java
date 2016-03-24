package com.puzzle.languine.ui.activity;

import android.os.Bundle;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.fragment.VideoFragment;
import com.puzzle.languine.utils.IntentConts;

public class VideoActivity extends MaterialActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_video);
        setupToolbar();

        String moduleName = getIntent().getStringExtra(IntentConts.MODULE_NAME);
        if(getIntent().hasExtra(IntentConts.VIDEO_ID)){
            playVideo();
        }else {
            introVideos(moduleName);
        }
    }

    private void playVideo() {
        int video_id = getIntent().getIntExtra(IntentConts.VIDEO_ID, 0);
        if(video_id!=0)
            addFragment(VideoFragment.newInstance(video_id));
    }

    private void introVideos(String moduleName){
        switch (moduleName)
        {
            case "Greetings":
                addFragment(VideoFragment.newInstance(R.raw.u01));
                break;
            case "Checking in":
                addFragment(VideoFragment.newInstance(R.raw.u02));
                break;
            case "Sightseeing":
                addFragment(VideoFragment.newInstance(R.raw.u03));
                break;
            case "Directions":
                addFragment(VideoFragment.newInstance(R.raw.u04));
                break;
            case "Eating":
                addFragment(VideoFragment.newInstance(R.raw.u05));
                break;
            case "Likes":
                addFragment(VideoFragment.newInstance(R.raw.u06));
                break;
            case "Planning":
                addFragment(VideoFragment.newInstance(R.raw.u07));
                break;
            case "Shopping":
                addFragment(VideoFragment.newInstance(R.raw.u08));
                break;
            case "Dating":
                addFragment(VideoFragment.newInstance(R.raw.u09));
                break;
            default:
                addFragment(VideoFragment.newInstance(R.raw.ttt));
        }
    }

}
