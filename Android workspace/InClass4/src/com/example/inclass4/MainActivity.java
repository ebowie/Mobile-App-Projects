/*
 * Assignment: In Class 4
 * File Name: InClassAssignment4.zip
 * Names: Eric Bowers and David Farynyk
 * Group: H
 */



package com.example.inclass4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	ProgressDialog progressDialog;
	ExecutorService threadPool;
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		threadPool = Executors.newFixedThreadPool(2);
		
		findViewById( R.id.button1 ).setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new DoWork().execute();
			}
		});
		
		findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				threadPool.execute(new DoWork2());
			}
		});
		
		
		
		handler = new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				
				switch( msg.what ) {
					case DoWork2.STATUS_START:
						progressDialog = new ProgressDialog( MainActivity.this );
						progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
						progressDialog.setCancelable( false );
						progressDialog.setTitle( "Doing work" );
						progressDialog.setMessage( "Retrieving the number..." );
						progressDialog.show();
						break;
					case DoWork2.STATUS_STEP:
						// Do nothing
						break;
					case DoWork2.STATUS_END:
						
						TextView myResult = (TextView) findViewById( R.id.textView1 );
						myResult.setText( Double.toString( (Double) msg.obj ) );
						progressDialog.dismiss();
						break;
				}
				
				return false;
			}
		});
		
	}
	
	class DoWork extends AsyncTask< Void, Void, Double > {

		@Override
		protected Double doInBackground(Void... params) {
			
			// Accomplish heavywork
			HeavyWork myWork = new HeavyWork();
			double result = myWork.getNumber();
			return result;
		}

		@Override
		protected void onPostExecute(Double result) {
			super.onPostExecute(result);
			
			TextView returnText = (TextView) findViewById( R.id.textView1 );
			returnText.setText( result.toString() );
			
			progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog( MainActivity.this );
			progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
			progressDialog.setCancelable( false );
			progressDialog.setTitle( "Doing work" );
			progressDialog.setMessage( "Retrieving the number..." );
			progressDialog.show();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
		
	}
	
	class DoWork2 implements Runnable {
		
		static final int STATUS_START = 0x00;
		static final int STATUS_STEP = 0x01;
		static final int STATUS_END = 0x02;

		@Override
		public void run() {
			
			Message msg = new Message();
			
			// Display progress bar
			msg.what = STATUS_START;
			handler.sendMessage(msg);
			
			// Do work
			HeavyWork myWork = new HeavyWork();
			double result = myWork.getNumber();
			
			// Pass message
			msg = new Message();
			msg.what = STATUS_END;
			msg.obj = (double)result;
			handler.sendMessage(msg);			
			
		}
		
	}
}
