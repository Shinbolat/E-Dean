package com.frank.dean.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.frank.dean.R;

import java.util.ArrayList;

/**
 * Created by shinbolat on 5/18/16.
 */
public class StudentsJournalFragment extends Fragment {

    View view;
    LinearLayout linearLayout;
    String name;
    Toolbar toolbar;

    ArrayList<String> subject_name, id;

    TextView subjectTextView;

    public StudentsJournalFragment(String name){

        this.name = name;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        toolbar = (Toolbar) getActivity().findViewById(R.id.journal_toolbar);
        toolbar.setTitle(R.string.journal);
        toolbar.setSubtitle(name);

        view = inflater.inflate(R.layout.journal_fragment, container, false);
        subjectTextView = (TextView)view.findViewById(R.id.name_textview);
        subjectTextView.setText(getContext().getResources().getString(R.string.subjects));

        return view;
    }

}
