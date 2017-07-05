package com.frank.dean.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.frank.dean.R;
import com.frank.dean.dekan.DekansChoiceFragment;
import com.frank.dean.teacher.JournalFragment;

/**
 * Created by shinbolat on 5/18/16.
 */
public class StudentActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.students_layout);

        name = getIntent().getStringExtra("name");

        Toolbar toolbar = (Toolbar) findViewById(R.id.journal_toolbar);
        toolbar.setTitle(R.string.journal);
        toolbar.setSubtitle(name);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.journal_fragment_container, new StudentsJournalFragment(name)).commit();


    }
}
