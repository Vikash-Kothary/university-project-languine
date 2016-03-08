package com.puzzle.appname;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Locale;

import static android.media.MediaFormat.createSubtitleFormat;

public class VideoFragment extends Fragment {
    private View view;

    public VideoFragment() {
        // Required empty public constructor
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void runVideo(int whichVideo){
        VideoView videoView = (VideoView)view.findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getContext().getPackageName() + "/" + whichVideo);
System.out.println((getContext().getResources().openRawResource(R.raw.subsu01)));
            videoView.addSubtitleSource(getContext().getResources().openRawResource(R.raw.subsu01), createSubtitleFormat("text/srt", Locale.ENGLISH.getLanguage()));

        videoView.setMinimumWidth(720);
        videoView.start();

        MediaController controller = new MediaController(getContext());
        controller.setAnchorView(videoView);
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;
    }


}
