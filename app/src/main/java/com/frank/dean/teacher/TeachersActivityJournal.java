package com.frank.dean.teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.frank.dean.R;
import com.frank.dean.teacher.ChoiceFragment;
import com.frank.dean.teacher.JournalFragment;

/**
 * Created by shinbolat on 5/10/16.
 */
public class TeachersActivityJournal extends AppCompatActivity implements ChoiceFragment.OnJournalSelectedListener {

    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = getIntent().getStringExtra("name");

        setContentView(R.layout.journal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.journal_toolbar);
        toolbar.setTitle(R.string.journal);
        toolbar.setSubtitle(name);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "name = " + name,Toast.LENGTH_LONG).show();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.journal_fragment_container, new ChoiceFragment()).commit();
    }

    public void OnJournalSelected(int pos){

        if(pos == 1){

            getSupportFragmentManager().beginTransaction().replace(R.id.journal_fragment_container, new JournalFragment(name)).addToBackStack(null).commit();

        }

        if(pos == 2){

            Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_LONG).show();

        }

    }
}
