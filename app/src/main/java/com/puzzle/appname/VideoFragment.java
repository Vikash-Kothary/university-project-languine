package com.puzzle.appname;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoFragment extends Fragment {
    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        VideoView videoView = (VideoView)view.findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" +this.getContext().getPackageName() + "/" + R.raw.ttt);
        videoView.setMinimumWidth(720);
        videoView.start();

        MediaController controller = new MediaController(this.getContext());
        controller.setAnchorView(videoView);
        controller.setMediaPlayer(videoView);

        videoView.setMediaController(controller);

        return view;
    }


}
