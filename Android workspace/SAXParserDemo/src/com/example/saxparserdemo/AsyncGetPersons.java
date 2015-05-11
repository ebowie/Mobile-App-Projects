package com.example.saxparserdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncGetPersons extends AsyncTask<String, Void, ArrayList<Person>>{

	@Override
	protected ArrayList<Person> doInBackground(String... params) {
	
		try {
			URL url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.connect();
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				//String text = IOUtils.toString(in);
				//Log.d("demo", text);
				return PersonUtils.PersonsSAXParser.parsePersons(in);
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
	protected void onPostExecute(ArrayList<Person> result) {
		super.onPostExecute(result);
		if(result != null){
			Log.d("demo", result.toString());	
		}
	}
}
