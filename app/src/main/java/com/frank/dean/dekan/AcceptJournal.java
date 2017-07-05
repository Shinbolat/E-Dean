package com.frank.dean.dekan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frank.dean.R;

/**
 * Created by shinbolat on 5/15/16.
 */
public class AcceptJournal extends Fragment{

    String name;
    Toolbar toolbar;

    public AcceptJournal(String name){

        this.name=name;

    }

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        toolbar = (Toolbar) getActivity().findViewById(R.id.journal_toolbar);

        view = inflater.inflate(R.layout.accept_journal_layout, container, false);

        toolbar.setTitle(R.string.manage_journal);
        toolbar.setSubtitle("");

        return view;
    }
}
