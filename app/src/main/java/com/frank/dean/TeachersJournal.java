package com.frank.dean;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by shinbolat on 5/10/16.
 */
public class TeachersJournal extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.journal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.journal_toolbar);
        toolbar.setTitle(R.string.journal);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "position --> " + getIntent().getIntExtra("position",-1),Toast.LENGTH_LONG).show();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.journal_fragment_container, new JournalFragment()).commit();
    }
}
