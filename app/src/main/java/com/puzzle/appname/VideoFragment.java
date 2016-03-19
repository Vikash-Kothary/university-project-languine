package com.puzzle.appname;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.puzzle.appname.Backend.Subtitles.Caption;
import com.puzzle.appname.Backend.Subtitles.FormatSRT;
import com.puzzle.appname.Backend.Subtitles.TimedTextObject;

import java.io.IOException;
import java.util.Collection;

public class VideoFragment extends Fragment implements MediaController.MediaPlayerControl, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {
    private View view;
    VideoView videoView;
    MediaPlayer mediaPlayer;
    private TimedTextObject srt;
    private SurfaceHolder sh;
    private SubtitleAsyncTask subAsync;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;
    }

    public void runVideo(int whichVideo) {
        videoView = (VideoView) view.findViewById(R.id.videoView);
        TextView txtDisplay = (TextView) view.findViewById(R.id.txtSubtitles);
        txtDisplay.setVisibility(View.INVISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            txtDisplay.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        final MediaController mediaController = new MediaController(this.getContext());
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this.getContext(), Uri.parse("android.resource://" + getContext().getPackageName() + "/" + whichVideo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sh = videoView.getHolder();
        sh.addCallback(this);
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(videoView);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mediaController.isShowing())
                    mediaController.hide();
                else
                    mediaController.show();
                return false;
            }
        });
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync();
        mediaPlayer.start();
        subAsync = (new SubtitleAsyncTask());
        subAsync.execute();
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mediaPlayer.setDisplay(holder);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        position = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
//        mediaPlayer.release();

    }

    public class SubtitleAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                FormatSRT formatSRT = new FormatSRT();
                srt = formatSRT.parseFile(getResources().getResourceName(R.raw.subsu01), getResources().openRawResource(R.raw.subsu01));
                subtitleDisplayHandler.post(subtitle);

            } catch (Exception e) {
                Log.e(getClass().getName(), e.getMessage(), e);
            }
            return null;
        }
    }

    private Handler subtitleDisplayHandler = new Handler();
    private Runnable subtitle = new Runnable() {
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                int currentPos = mediaPlayer.getCurrentPosition();
                Collection<Caption> subtitles = srt.captions.values();
                for (Caption caption : subtitles) {
                    if (currentPos >= caption.start.getMseconds() && currentPos <= caption.end.getMseconds()) {
                        onTimedText(caption);
                        break;
                    } else if (currentPos > caption.end.getMseconds()) {
                        onTimedText(null);
                    }
                }
            }
            long SUBTITLE_DISPLAY_CHECK = 100;
            subtitleDisplayHandler.postDelayed(this, SUBTITLE_DISPLAY_CHECK);
        }

    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Position", position);
    }

    public int position = 0;

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        super.onViewStateRestored(savedInstanceState);
        position = savedInstanceState != null ? savedInstanceState.getInt("Position") : 0;
        mediaPlayer.seekTo(position);
        mediaPlayer.start();
    }


    public void onTimedText(Caption text) {
        TextView txtDisplay = (TextView) view.findViewById(R.id.txtSubtitles);
        if (text == null) {
            txtDisplay.setVisibility(View.INVISIBLE);
            return;
        }
        txtDisplay.setText(Html.fromHtml(text.content));
        txtDisplay.setVisibility(View.VISIBLE);
    }

}
