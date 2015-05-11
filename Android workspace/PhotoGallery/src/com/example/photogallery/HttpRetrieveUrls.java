/*
 * Assignment: Homework 3
 * File name: HW3.zip (PhotoGallery)
 * Names: David Farynyk and Eric Bowers
 * 
 * Class: Mobile Application and Development (ITCS 5180), Summer I 2014
 * Due: 6/15/2014
 * Description: Photo Gallery which retrieves URLs from the internet and displays photos
 */


package com.example.photogallery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HttpRetrieveUrls extends AsyncTask<String, Void, ArrayList<String> > {

	public Handler handler;
	int SHOW_PROGRESS = 0;
	int HIDE_PROGRESS = 1;
	int RETURN_URLS = 2;
	
	@Override
	protected void onPostExecute(ArrayList<String> result) {
		super.onPostExecute(result);
		
		Message msg = new Message();
		msg.what = HIDE_PROGRESS;
		handler.sendMessage( msg );
		
		msg = new Message();
		msg.what = RETURN_URLS;
		msg.obj = result;
		handler.sendMessage( msg );
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		Message msg = new Message();
		msg.what = SHOW_PROGRESS;
		msg.obj = "Loading image URLs";
		// Log.d("test", "Called: " + handler );
		handler.sendMessage( msg );
		
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected ArrayList<String> doInBackground(String... params) {
		
		// Log.d( "demo", "In Async" );
		// Log.d("demo", params[0]);
		
		try {
			ArrayList<String> output = new ArrayList<String>();
			URL url = new URL( params[0] );
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			int statusCode = con.getResponseCode();
			if( statusCode == HttpURLConnection.HTTP_OK ) {
				InputStream in = con.getInputStream();
				BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
				String line;
				while( ( line = reader.readLine() ) != null ) {
					output.add( line );
				}
				reader.close();
				// Log.d( "demo", "Output: " + output.toString() );
				return output;
			}
			con.disconnect();
		} catch ( MalformedURLException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		
		return null;
	}

}
