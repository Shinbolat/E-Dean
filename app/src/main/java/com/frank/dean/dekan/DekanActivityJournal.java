package com.frank.dean.dekan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.frank.dean.R;

/**
 * Created by shinbolat on 5/15/16.
 */
public class DekanActivityJournal extends AppCompatActivity implements DekansChoiceFragment.OnDekansItemSelectedListener{

    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dekan_layout);

        name = getIntent().getStringExtra("name");

        Toolbar toolbar = (Toolbar) findViewById(R.id.journal_toolbar);
        toolbar.setTitle(R.string.journal);
        toolbar.setSubtitle(name);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.journal_fragment_container, new DekansChoiceFragment()).commit();

    }

    @Override
    public void OnDekansItemSelected(int pos) {

        if(pos == 1){

            getSupportFragmentManager().beginTransaction().replace(R.id.journal_fragment_container, new ManageJournal(name)).addToBackStack(null).commit();

        }

        if(pos == 2){

            getSupportFragmentManager().beginTransaction().replace(R.id.journal_fragment_container, new AcceptJournal(name)).addToBackStack(null).commit();

        }
    }
}
