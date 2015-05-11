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

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HttpRetrievePhoto extends AsyncTask< String, Void, Bitmap > {

	Handler handler;
	int SHOW_PROGRESS = 0;
	int HIDE_PROGRESS = 1;
	Bitmap image;
	
	@Override
	protected Bitmap doInBackground(String... arg0) {
		
		try {
			URL url = new URL( arg0[0] );
			image = BitmapFactory.decodeStream( url.openStream() );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		return image;

	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		
		Message msg = new Message();
		msg.what = HIDE_PROGRESS;
		msg.obj = result;
		Log.d("demo", "Picture result: " + result );
		handler.sendMessage( msg );

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		Message msg = new Message();
		msg.what = SHOW_PROGRESS;
		msg.obj = "Loading image";
		handler.sendMessage( msg );
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}
	
}
