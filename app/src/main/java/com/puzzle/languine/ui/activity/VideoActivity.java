package com.puzzle.languine.ui.activity;

import android.os.Bundle;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.ModuleData;
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
        if (getIntent().hasExtra(IntentConts.VIDEO_LINK)) {
            playVideo();
        } else {
            ModuleData videoIDs = new ModuleData();
            addFragment(VideoFragment.newInstance(videoIDs.getModule(moduleName).getVideoLink()));
        }
    }

    private void playVideo() {
        String videoLink = getIntent().getStringExtra(IntentConts.VIDEO_LINK);
        if (videoLink != null)
            addFragment(VideoFragment.newInstance(videoLink));
    }

//    private void introVideos(String moduleName){
//        switch (moduleName)
//        {
//            case "Greetings":
//                addFragment(VideoFragment.newInstance(R.raw.u01));
//                break;
//            case "Checking in":
//                addFragment(VideoFragment.newInstance(R.raw.u02));
//                break;
//            case "Sightseeing":
//                addFragment(VideoFragment.newInstance(R.raw.u03));
//                break;
//            case "Directions":
//                addFragment(VideoFragment.newInstance(R.raw.u04));
//                break;
//            case "Eating":
//                addFragment(VideoFragment.newInstance(R.raw.u05));
//                break;
//            case "Likes":
//                addFragment(VideoFragment.newInstance(R.raw.u06));
//                break;
//            case "Planning":
//                addFragment(VideoFragment.newInstance(R.raw.u07));
//                break;
//            case "Shopping":
//                addFragment(VideoFragment.newInstance(R.raw.u08));
//                break;
//            case "Dating":
//                addFragment(VideoFragment.newInstance(R.raw.u09));
//                break;
//            default:
//                addFragment(VideoFragment.newInstance(R.raw.ttt));
//        }
//    }

}
