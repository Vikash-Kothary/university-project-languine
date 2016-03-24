package com.puzzle.languine.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class EnglishGlossaryFragment extends MaterialFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EnglishGlossaryFragment newInstance(int sectionNumber) {
        EnglishGlossaryFragment fragment = new EnglishGlossaryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public EnglishGlossaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.glossary_spanish_fragment, container, false);

        TextView textView = (TextView) findViewById(R.id.section_label);
        textView.setText(getArguments().getInt(ARG_SECTION_NUMBER));

        return rootView;
    }
}
