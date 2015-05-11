package com.example.midterm_app;
/*
 * MidTerm: RottonTomatos app
 * File: MidTerm.zip
 * Name:Eric Bowers
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GetAsyncTaskMovie extends AsyncTask<String, Void, ArrayList<movieTabs>> {

    Handler handler;
    int SHOW_PROGRESS = 0;
    int HIDE_PROGRESS = 1;
    MovieActivity activity;
    GetAsyncTaskMovie(MovieActivity activity){
        this.activity = activity;
    }


    ArrayList<movieTabs> movies;
    Message msg = new Message();

    @Override
    protected ArrayList<movieTabs> doInBackground(String... params) {
        // TODO Auto-generated method stub


        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                String text = IOUtils.toString(in);
                return movieUtils.MovieJSONParser.parse(text);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        Message msg = new Message();
        msg.what = SHOW_PROGRESS;
        msg.obj = "Loading image";
        handler.sendMessage( msg );



    }

    @Override
    protected void onPostExecute(ArrayList<movieTabs> result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        Message msg = new Message();
        msg.what = HIDE_PROGRESS;
        msg.obj = result;
        handler.sendMessage( msg );
        activity.setData(result);


        if (result != null) {
            Log.d("demo", result.toString());
        }

    }



}
