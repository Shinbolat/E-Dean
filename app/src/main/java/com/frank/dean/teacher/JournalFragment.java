package com.frank.dean.teacher;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.frank.dean.AsyncTask.NetAsyncTask;
import com.frank.dean.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by shinbolat on 5/10/16.
 */
public class JournalFragment extends Fragment {

    View view;
    LinearLayout linearLayout;
    String name, subject, group;
    Toolbar toolbar;
    Spinner subjectSpinner, groupSpinner;
    Context context;

    ArrayList<String> groupNameList, subjectNameList;

    public JournalFragment(String name){

        this.name = name;
/*


*/

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        toolbar = (Toolbar) getActivity().findViewById(R.id.journal_toolbar);

        view = inflater.inflate(R.layout.journal_fragment, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.journal);
        Log.i("ONCREATEVIEW = ", "THERE ARE!!!" + name );

        FillSubject();

        toolbar.setTitle(R.string.journal);
        toolbar.setSubtitle("");

        return view;
    }

    private ArrayList<String> StudentsName(String group){

        JSONObject studentsNameJsonObject;
        JSONArray namesJsonArray;
        ArrayList<String> nameArrayList = new ArrayList<>();

        try {

            studentsNameJsonObject = new JSONObject(new NetAsyncTask(getContext())
                    .execute("http://10.0.2.2/testing/example.php",
                            "select id, name from student where group_name ='"+ group +"'").get());

            namesJsonArray = studentsNameJsonObject.getJSONArray("result");

            if(!namesJsonArray.isNull(0)){

                for (int i = 0; i< namesJsonArray.length(); i ++){

                    Log.i("STUDENT LIST = ",namesJsonArray.optJSONObject(i).getString("temp"));
                    nameArrayList.add(namesJsonArray.optJSONObject(i).getString("temp"));


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return nameArrayList;
    }

    private void FillSubject(){

        subjectSpinner = new Spinner(getContext());
        groupSpinner = new Spinner(getContext());

        JSONObject subjectJson, groupJson;

        JSONArray subjectArrayJson, groupArrayJson;

        try {

            subjectJson = new JSONObject(new NetAsyncTask(getContext())
                    .execute("http://10.0.2.2/testing/example.php",
                            "select id, subject_name from teacher_subject_group where teacher_name = '" + name+ "' GROUP BY subject_name").get());

            subjectArrayJson = subjectJson.getJSONArray("result");
            Log.i("RESULT",subjectJson.toString());

            subjectNameList = new ArrayList<String>();
            groupNameList =  new ArrayList<String>();

            if(!subjectArrayJson.isNull(0)) {

                for (int i = 0; i< subjectArrayJson.length(); i ++){

                    Log.i("SUBJECT = ",subjectArrayJson.optJSONObject(i).getString("temp"));
                    subjectNameList.add(subjectArrayJson.optJSONObject(i).getString("temp"));


                }

                ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(getContext(),R.layout.simple_spinner_dropdown_item, subjectNameList);

                subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                subjectSpinner.setAdapter(subjectAdapter);

                toolbar.addView(subjectSpinner);

            }

            groupJson = new JSONObject(new NetAsyncTask(getContext())
                    .execute("http://10.0.2.2/testing/example.php",
                            "select id, group_name from teacher_subject_group where teacher_name = '" + name+ "' GROUP BY group_name").get());

            groupArrayJson = groupJson.getJSONArray("result");
            Log.i("RESULT",groupJson.toString());

            if(!groupArrayJson.isNull(0)) {

                for (int i = 0; i< groupArrayJson.length(); i ++){

                    Log.i("GROUP = ", groupArrayJson.optJSONObject(i).getString("temp"));
                    groupNameList.add(groupArrayJson.optJSONObject(i).getString("temp"));

                }

                ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(getContext(),R.layout.simple_spinner_dropdown_item, groupNameList);

                groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                groupSpinner.setAdapter(groupAdapter);

                toolbar.addView(groupSpinner);

            }

            //Log.i("SUBJECTS", subjectJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subject = ((TextView) view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                group = ((TextView)view).getText().toString();

                ArrayList<String> tempStudentList = StudentsName(group);

                //there is generate a journal
                for(int i = 0; i < tempStudentList.size(); i ++){

                    linearLayout.addView(getLinearLayout(tempStudentList.get(i),"","","",""));

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    private void FillGroup(){


    }

    LinearLayout getLinearLayout(String name, String mod1, String mod2, String finwork, String roll){


        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        //Log.i("H + W", height + " " + width);

        /*------------------------------------------------------*/
        //Size of elements at the head

        TextView nameTextViewTemp, module1Temp, module2Temp, finishWorkTemp, rollTemp;
        nameTextViewTemp = (TextView)view.findViewById(R.id.name_textview);
        module1Temp = (TextView)view.findViewById(R.id.modul_1_textview);
        module2Temp = (TextView)view.findViewById(R.id.modul_2_textview);
        finishWorkTemp = (TextView)view.findViewById(R.id.finish_mark_textview);
        rollTemp = (TextView)view.findViewById(R.id.roll_textview);

        nameTextViewTemp.setWidth(3*width/7);
        module1Temp.setWidth(width/7);
        module2Temp.setWidth(width/7);
        finishWorkTemp.setWidth(width/7);
        rollTemp.setWidth(width/7);
/*

        nameTextViewTemp.setTa;
        module1Temp.;
        module2Temp.;
        finishWorkTemp.;
        rollTemp.
*/

        /*------------------------------------------------------*/

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView nameTextView = new TextView(getContext());
        nameTextView.setText(name);
        nameTextView.setLines(1);
        nameTextView.setEllipsize(TextUtils.TruncateAt.END);
        nameTextView.setLayoutParams(new LinearLayout.LayoutParams(3*width/7, LinearLayout.LayoutParams.WRAP_CONTENT));


        EditText firstModuleEditText, secondModuleEditText, finWorkEditText, rollEditText;
        firstModuleEditText = new EditText(getContext());
        secondModuleEditText = new EditText(getContext());
        finWorkEditText = new EditText(getContext());
        rollEditText = new EditText(getContext());

        firstModuleEditText.setText(mod1);
        secondModuleEditText.setText(mod2);
        finWorkEditText.setText(finwork);
        rollEditText.setText(roll);

        firstModuleEditText.setLayoutParams(new LinearLayout.LayoutParams(width/7, LinearLayout.LayoutParams.WRAP_CONTENT));
        secondModuleEditText.setLayoutParams(new LinearLayout.LayoutParams(width/7, LinearLayout.LayoutParams.WRAP_CONTENT));
        finWorkEditText.setLayoutParams(new LinearLayout.LayoutParams(width/7, LinearLayout.LayoutParams.WRAP_CONTENT));
        rollEditText.setLayoutParams(new LinearLayout.LayoutParams(width/7, LinearLayout.LayoutParams.WRAP_CONTENT));

        nameTextView.setId(1);
        firstModuleEditText.setId(2);
        secondModuleEditText.setId(3);
        finWorkEditText.setId(4);
        rollEditText.setId(5);

        firstModuleEditText.setMaxEms(2);
        secondModuleEditText.setMaxEms(2);
        finWorkEditText.setMaxEms(2);
        rollEditText.setMaxEms(1);

        firstModuleEditText.setLines(1);
        secondModuleEditText.setLines(1);
        finWorkEditText.setLines(1);
        rollEditText.setLines(1);

        firstModuleEditText.setGravity(17);
        secondModuleEditText.setGravity(17);
        finWorkEditText.setGravity(17);
        rollEditText.setGravity(17);

        layout.addView(nameTextView);
        layout.addView(firstModuleEditText);
        layout.addView(secondModuleEditText);
        layout.addView(finWorkEditText);
        layout.addView(rollEditText);

        return layout;
    }

}
