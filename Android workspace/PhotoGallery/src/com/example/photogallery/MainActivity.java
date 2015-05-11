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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	ArrayList<String> photoURLs;
	Handler progressHandler;
	final int SHOW_PROGRESS = 0;
	final int HIDE_PROGRESS = 1;
	final int RETURN_URLS = 2;
	final int LAUNCH_PHOTOS = 0;
	final int LAUNCH_SLIDESHOW = 1;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressHandler = new Handler( new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				
				switch( msg.what ) {
					case SHOW_PROGRESS:
						progressDialog = new ProgressDialog( MainActivity.this );
						progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
						progressDialog.setCancelable( false );
						progressDialog.setMessage( (String) msg.obj );
						progressDialog.show();
						break;
					case HIDE_PROGRESS:
						progressDialog.hide();
						break;
					case RETURN_URLS:
						photoURLs = ( ArrayList<String> ) msg.obj;
						break;
				}
				
				return false;
			}
		});
		
		// Set button listener for both buttons
		Button photosButton = (Button) findViewById( R.id.button1 );
		photosButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Log.d( "demo","From the main activity" );
				Intent i = new Intent( MainActivity.this, PhotoActivity.class );
				i.putExtra( "launchType", LAUNCH_PHOTOS );
				i.putExtra( "URLs", photoURLs );
				startActivity( i );
			}
		});
		
		Button slideshowButton = (Button) findViewById( R.id.button2 );
		slideshowButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Log.d( "demo","From the main activity" );
				Intent i = new Intent( MainActivity.this, PhotoActivity.class );
				i.putExtra( "launchType", LAUNCH_SLIDESHOW );
				i.putExtra( "URLs", photoURLs );
				startActivity( i );
			}
		});
		
		// Retrieve URLs for the photos
		HttpRetrieveUrls getUrls = new HttpRetrieveUrls();
		getUrls.handler = progressHandler;
		getUrls.execute( "http://liisp.uncc.edu/~mshehab/api/photos.txt" );
		
	}
	

}
