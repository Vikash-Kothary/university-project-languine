package com.puzzle.appname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MultipleChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_multiple_choice);

        TextView titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText(R.string.multipleChoiceTitle);

        TextView btext = (TextView) findViewById(R.id.textb);
        btext.setText(R.string.Question);

        // add the questions and answers in intent so that they can be directly added to the activity
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        RadioButton radioButton = new RadioButton(this);
        radioButton.setText("Answer 1");
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setText("Answer 2");
        RadioButton radioButton3 = new RadioButton(this);
        radioButton3.setText("Answer 3");
        radioGroup.addView(radioButton);
        radioGroup.addView(radioButton2);
        radioGroup.addView(radioButton3);

    }
}
