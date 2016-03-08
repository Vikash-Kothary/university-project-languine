package com.puzzle.appname;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class TextQuestionFragment extends Fragment {

    public TextQuestionFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_question, container, false);
        TextView titleText = (TextView) view.findViewById(R.id.question);
        titleText.setText("Girls");


        // add the questions and answers in intent so that they can be directly added to the activity
        ViewGroup Group = (ViewGroup) view.findViewById(R.id.possible_answers);
        Button Button;
        Button = new Button(this.getContext());
        Button.setText("Answer 1");
        Button Button2 = new Button(this.getContext());
        Button2.setText("Answer 2");
        Button Button3 = new Button(this.getContext());
        Button3.setText("Answer 3");
        Group.addView(Button);
        Group.addView(Button2);
        Group.addView(Button3);

        return view;



    }
}
