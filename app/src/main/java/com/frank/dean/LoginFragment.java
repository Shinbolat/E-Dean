package com.frank.dean;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.TimeUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frank.dean.AsyncTask.NetAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by shinbolat on 5/6/16.
 */
public class LoginFragment extends Fragment {


    int position;
    String role[];
    LayoutInflater inflater;
    View view;

    Button next;
    TextView login, password, message;


    public LoginFragment(int position){

        this.position=position;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        role = getContext().getResources().getStringArray(R.array.roles);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getContext().getResources().getString(R.string.auth) + " - " + (getContext().getResources().getStringArray(R.array.roles))[position]);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.login, container, false);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        next = (Button)view.findViewById(R.id.accept);
        login = (TextView) view.findViewById(R.id.login);
        password = (TextView)view.findViewById(R.id.password);
        message = (TextView)view.findViewById(R.id.message);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String log = login.getText().toString();
                String passw = password.getText().toString();
                JSONObject result;

                Toast.makeText(getContext(),"select login, passw from "
                        + role[position].toLowerCase()
                        + " where login = \'" + log + "\' and passw = \'" + passw + "\'",Toast.LENGTH_LONG).show();

                if(!log.isEmpty() && (!passw.isEmpty())){
                    try {
                        result = new JSONObject(new NetAsyncTask(getContext())
                                                    .execute("http://localhost/testing/login.php", "select login, passw from "
                                                            + role[position].toLowerCase()
                                                            + " where login=\'" + log + "\' and passw=\'" + passw + "\'").get());


                        //"select login, passw from teacher where login='Pak' and passw='pak'"

                        JSONArray authArray = result.getJSONArray("result");
                        JSONObject auth = authArray.optJSONObject(0);


                        if(!authArray.isNull(0)) {
                            if ((!(auth.getString("login").isEmpty()))
                                    && (log.toLowerCase().equals(auth.getString("login").toLowerCase()))
                                    && (passw.toLowerCase().equals(auth.getString("password").toLowerCase()))) {

                                Log.i("JSON", auth.getString("login") + " " + auth.getString("password"));

                                message.setText(getContext().getResources().getString(R.string.success));

                                Intent intent = new Intent(getContext(), TeachersJournal.class);
                                intent.putExtra("position",position);
                                startActivity(intent);

                            }
                            else message.setText(getContext().getResources().getString(R.string.error));
                        }
                        else message.setText(getContext().getResources().getString(R.string.error));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else{

                    Toast.makeText(getContext(),getContext().getResources()
                            .getString(R.string.warn),Toast.LENGTH_LONG).show();

                    //message.setTextColor(getContext().getColor(R.color.red));
                    //message.setText(getContext().getResources().getString(R.string.warn));
                }

            }
        });

    }

    OnSuccesListener onSuccesListener;

    public interface OnSuccesListener {
        public void OnSuccesListener(int pos);
    }

}
