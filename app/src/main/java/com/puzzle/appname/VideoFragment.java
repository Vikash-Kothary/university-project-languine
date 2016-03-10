package com.puzzle.appname;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class VideoFragment extends Fragment implements SurfaceHolder.Callback,MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnVideoSizeChangedListener, MediaController.MediaPlayerControl, MediaPlayer.OnTimedTextListener {
    private View view;
    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    private MediaController mediaController;
    private Handler handler = new Handler();
    private TextView txtDisplay;

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

        txtDisplay = (TextView) view.findViewById(R.id.subTextView);
        surfaceView = (SurfaceView)view.findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(176, 144);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mediaController = new MediaController(this.getContext());

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnTimedTextListener(this);
        if(mediaPlayer.isPlaying()){
            mediaPlayer.reset();
        }

        try {
            mediaPlayer.setDataSource(getContext().getApplicationContext(), Uri.parse( "android.resource://" + getContext().getPackageName() + "/" +whichVideo ));
            try {
                mediaPlayer.addTimedTextSource(getSubtitleFile(R.raw.subsu01),
                        MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP);
            } catch (IOException e) {
                e.printStackTrace();
            }
            final int textTrackIndex = findTrackIndexFor(
                    MediaPlayer.TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT,mediaPlayer.getTrackInfo());
            if (textTrackIndex >= 0) {
                mediaPlayer.selectTrack(textTrackIndex);
            } else {
                Log.w("cake", "Cannot find text track!");
            }

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();
        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*
                * the MediaController will hide after 3 seconds - tap the screen to
                * make it appear again
                */
                mediaController.show();
                return false;
            }
        });
        mediaPlayer.start();
    }


    private int findTrackIndexFor(int mediaTrackType, MediaPlayer.TrackInfo[] trackInfo) {
        int index = -1;
        for (int i = 0; i < trackInfo.length; i++) {
            if (trackInfo[i].getTrackType() == mediaTrackType) {
                return i;
            }
        }
        return index;
    }

    private String getSubtitleFile(int resId) {
        String fileName = getResources().getResourceEntryName(resId);
        File subtitleFile = getContext().getFileStreamPath(fileName);
        if (subtitleFile.exists()) {
            Log.d("cake", "Subtitle already exists");
            return subtitleFile.getAbsolutePath();
        }
        Log.d("cake", "Subtitle does not exists, copy it from res/raw");

        // Copy the file from the res/raw folder to your app folder on the
        // device
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = getResources().openRawResource(resId);
            outputStream = new FileOutputStream(subtitleFile, false);
            copyFile(inputStream, outputStream);
            return subtitleFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStreams(inputStream, outputStream);
        }
        return "";
    }

    private void copyFile(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        final int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        int length = -1;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
    }

    // A handy method I use to close all the streams
    private void closeStreams(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable stream : closeables) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onTimedText(final MediaPlayer mp, final TimedText text) {
        if (text != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int seconds = mp.getCurrentPosition() / 1000;

                    txtDisplay.setText("[" + secondsToDuration(seconds) + "] "
                            + text.getText());
                }
            });
        }
    }

    // To display the seconds in the duration format 00:00:00
    public String secondsToDuration(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600,
                (seconds % 3600) / 60, (seconds % 60), Locale.US);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mediaPlayer.release();
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        mediaPlayer.setDisplay(surfaceHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }


    public void start() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d("cake", "onPrepared called");
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(view.findViewById(R.id.surfaceview));
        handler.post(new Runnable() {

            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }
}
