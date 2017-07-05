package com.frank.dean.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by shinbolat on 4/29/16.
 */
public class NetAsyncTask extends AsyncTask<String, Void,String> {

    Context context;

    public NetAsyncTask(Context context){

        this.context=context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String[] params) {

        URL url;
        StringBuilder input = null;

        try {
            url = new URL(params[0].toString());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            input =new StringBuilder();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Encoding", "identity");
            //connection.setRequestProperty("Content-Type", "application/json");
            connection.setChunkedStreamingMode(0);


            Log.i("TASK", ".......");

            //---------------SEND REQUEST TO SERVER----------------------
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

            String data = URLEncoder.encode("query_text", "UTF-8")
                    + "=" + URLEncoder.encode(params[1].toString(), "UTF-8");
            writer.write(data);

            /*String str = "{\"query_text\":"+ params[1].toString() + "}";
            writer.write(URLEncoder.encode((str), "UTF-8"));
            */
            writer.flush();

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line="";

            while ((line = reader.readLine()) != null) {
                input.append(line + " ");
            }
            Log.i("RESPONSE",input.toString());

            reader.close();

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (input!=null)?input.toString():"NULLLL";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
