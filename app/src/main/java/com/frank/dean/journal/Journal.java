package com.frank.dean.journal;

import android.content.Context;

import com.frank.dean.AsyncTask.NetAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by shinbolat on 5/22/16.
 */
public class Journal {

    Context context;
    JSONObject result;


    public Journal(Context context){

        this.context=context;

    }

    ArrayList<String> nameOfStudents(String teacher, String group, String subject){

        try {
            result = new JSONObject(new NetAsyncTask(context)
                    .execute("http://10.0.2.2/testing/example.php",
                            "select id, subject_name, group_name from teacher_subject_group where teacher_name = '" + teacher+ "' and group_name = '" + group + "' and subject_name = '" + subject + "'").get());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return null;
    }
}
