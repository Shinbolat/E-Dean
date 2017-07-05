package com.frank.dean;

import android.app.FragmentTransaction;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.frank.dean.AsyncTask.NetAsyncTask;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.OnArticleSelectedListener{

    String input=null;
    NetAsyncTask netAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.role);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new MainActivityFragment()).commit();

    }

    @Override
    public void onArticleSelected(int pos){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new LoginFragment(pos)).addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
