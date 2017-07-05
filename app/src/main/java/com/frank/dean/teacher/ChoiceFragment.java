package com.frank.dean.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frank.dean.R;

/**
 * Created by shinbolat on 5/10/16.
 */
public class ChoiceFragment extends Fragment {

    View view;

    Button journal, roll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.journal_toolbar);
        toolbar.setTitle(R.string.journal);

        view = inflater.inflate(R.layout.choice_layout, container, false);

        journal = (Button)view.findViewById(R.id.journal);
        roll = (Button)view.findViewById(R.id.roll);

        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                journalSelectedListener.OnJournalSelected(1);
            }
        });

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                journalSelectedListener.OnJournalSelected(2);
            }
        });

        return view;
    }

    OnJournalSelectedListener journalSelectedListener;

    public interface OnJournalSelectedListener {
        public void OnJournalSelected(int pos);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        journalSelectedListener = (OnJournalSelectedListener)activity;
    }
}
