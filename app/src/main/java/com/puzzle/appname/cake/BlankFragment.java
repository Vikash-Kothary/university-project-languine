package com.puzzle.appname.cake;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.puzzle.appname.GetStarted;
import com.puzzle.appname.R;

public class BlankFragment extends Fragment {
    public static final String LESSON_TITLE = "TITLE";
    public static final String LESSON_NUMBER = "NUMBER";
    private int lessonImageID;
    private String lessonName;
    private int progress = 0;
    private View view;
    ImageView  lessonImageView;
    TextView lessonNameView;
    TextView progessView;
    private int position;

    public BlankFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.card_view_lesson, container, false);
        lessonImageView = (ImageView)view.findViewById(R.id.thumbnail);
        lessonNameView = (TextView)view.findViewById(R.id.module_title);
        progessView = (TextView)view.findViewById(R.id.module_description);
        lessonImageView = (ImageView)view.findViewById(R.id.festividad_thumbnail);
        //lessonNameView = (TextView)view.findViewById(R.id.lessonTitle);
        //progessView = (TextView)view.findViewById(R.id.progress);
        lessonImageView.setImageResource(lessonImageID);
        lessonNameView.setText(lessonName);
        progessView.setText(String.format("%d%%", progress));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlankFragment.this.getContext(), GetStarted.class);
                intent.putExtra(LESSON_TITLE, lessonName);
                intent.putExtra("WhichVideo", position);
                intent.putExtra(LESSON_NUMBER, (position + 1) + "");
                startActivity(intent);
            }
        });
        return view;
    }

    public void setLessonImageID(int lessonImageID) {
        this.lessonImageID = lessonImageID;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setProgress(int progress) {
        if (progress > 0 && progress <= 100) {
            this.progress = progress;
        } else {
            this.progress = 0;
        }
    }

    public void setPosition(int i) {
        this.position = i;
    }
}
