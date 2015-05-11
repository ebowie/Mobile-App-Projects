package com.example.pullparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncGetPerson extends AsyncTask<String, Void, Person> {

    @Override
    protected Person doInBackground(String... params) {
        // TODO Auto-generated method stub

       try {
        URL url = new URL(params[0]);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        int statusCode = con.getResponseCode();
        if (statusCode == HttpURLConnection.HTTP_OK) {
            InputStream in = con.getInputStream();
            String text = IOUtils.toString(in);
           return PersonUtils.PersonPULLParser.parsePerson(in);
        }  else {
            Log.d("demo", "Did not enter if statement");
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
    protected void onPostExecute(Person result) {
        super.onPostExecute(result);

        if(result != null) {
            Log.d("demo",result.toString());
        } else {
            Log.d("demo","Null Person");
        }
    }

}
