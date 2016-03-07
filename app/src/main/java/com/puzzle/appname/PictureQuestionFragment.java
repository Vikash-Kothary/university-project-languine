package com.puzzle.appname;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PictureQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PictureQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureQuestionFragment extends Fragment  implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public PictureQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PictureQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PictureQuestionFragment newInstance(String param1, String param2) {
        PictureQuestionFragment fragment = new PictureQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imgView1 = (ImageView) getActivity().findViewById(R.id.picture1);
        ImageView imgView2 = (ImageView) getActivity().findViewById(R.id.picture2);
        ImageView imgView3 = (ImageView) getActivity().findViewById(R.id.picture3);
        ImageView imgView4 = (ImageView) getActivity().findViewById(R.id.picture4);
        ImageView imgView5 = (ImageView) getActivity().findViewById(R.id.picture5);
        ImageView imgView6 = (ImageView) getActivity().findViewById(R.id.picture6);

        imgView1.setOnClickListener(this);
        imgView2.setOnClickListener(this);
        imgView3.setOnClickListener(this);
        imgView4.setOnClickListener(this);
        imgView5.setOnClickListener(this);
        imgView6.setOnClickListener(this);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_question, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
