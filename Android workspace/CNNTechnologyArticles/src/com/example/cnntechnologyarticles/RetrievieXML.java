package com.example.cnntechnologyarticles;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.os.AsyncTask;

public class RetrievieXML extends AsyncTask<String, Void, ArrayList<CNN>> {
    NewsActivity activity;
    public RetrievieXML(NewsActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<CNN> doInBackground(String... params) {
        // TODO Auto-generated method stub



        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                return CNNUtril.parseXML.parse(in);
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
    protected void onPostExecute(ArrayList<CNN> result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        activity.setData(result);


    }



}
