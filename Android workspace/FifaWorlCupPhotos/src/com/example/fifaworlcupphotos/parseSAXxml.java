package com.example.fifaworlcupphotos;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class parseSAXxml extends AsyncTask<String, Void, ArrayList<Article>> {
    MainActivity activity;
    PhotoActivity activity2;
    Handler handler2;
    int SHOW_PROGRESS = 0;
    int SHOW_DISMISS = 1;

    Message msg = new Message();
    public parseSAXxml(MainActivity activity){
        this.activity = activity;

    }



    @Override
    protected ArrayList<Article> doInBackground(String... params) {
        // TODO Auto-generated method stub

        URL url;
        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                return ArticleUtil.ArticleSAXParser.parseArticles(in);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




        return null;
    }
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        msg = new Message();
        msg.what = SHOW_PROGRESS;
        handler2.sendMessage(msg);
    }


    @Override
    protected void onPostExecute(ArrayList<Article> result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        msg = new Message();
        msg.what = SHOW_DISMISS;
        handler2.sendMessage(msg);

        activity.setData(result);



    }
}
