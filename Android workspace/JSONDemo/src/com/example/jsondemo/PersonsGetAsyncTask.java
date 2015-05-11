package com.example.jsondemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

public class PersonsGetAsyncTask extends AsyncTask<String, Void, LinkedList<Person>>{

	@Override
	protected LinkedList<Person> doInBackground(String... params) {
		try {
			URL url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();			
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				String text = IOUtils.toString(in);				
				return PersonUtils.PersonsJSONParser.parse(text);
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
	protected void onPostExecute(LinkedList<Person> result) {
		super.onPostExecute(result);
		if(result != null){
			Log.d("demo", result.toString());
		}
	}
	
	
	
}
