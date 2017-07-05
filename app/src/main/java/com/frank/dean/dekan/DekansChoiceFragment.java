package com.frank.dean.dekan;

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
 * Created by shinbolat on 5/15/16.
 */
public class DekansChoiceFragment extends Fragment {

    View view;

    Button manageJournal, acceptJournal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.journal_toolbar);
        toolbar.setTitle(R.string.journal);

        view = inflater.inflate(R.layout.choice_layout, container, false);

        manageJournal = (Button)view.findViewById(R.id.journal);
        acceptJournal = (Button)view.findViewById(R.id.roll);

        manageJournal.setText(getContext().getResources().getString(R.string.manage_journal));
        acceptJournal.setText(getContext().getResources().getString(R.string.accept_journal));

        manageJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.OnDekansItemSelected(1);
            }
        });

        acceptJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.OnDekansItemSelected(2);
            }
        });

        return view;
    }

    OnDekansItemSelectedListener itemSelectedListener;

    public interface OnDekansItemSelectedListener {
        public void OnDekansItemSelected(int pos);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        itemSelectedListener = (OnDekansItemSelectedListener)activity;
    }
}
