/*
 * Assignment: In Class 5
 * File Name: InClassAssignment6.zip
 * Students: David Farynyk and Eric Bowers
 */

package com.example.cnntech;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	public static DataManager dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button newsButton = (Button) findViewById( R.id.button1 );
		newsButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent( MainActivity.this, NewsActivity.class );
				i.putExtra( "type", 0 );
				startActivity( i );
			}
		});
		
		Button likesButton = (Button) findViewById( R.id.button2 );
		likesButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent( MainActivity.this, NewsActivity.class );
				i.putExtra( "type", 1 );
				startActivity( i );
			}
		});
		
		Button clearLikesButton = (Button) findViewById( R.id.button3 );
		clearLikesButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
	}
	
	@Override
	protected void onDestroy() {
		dm.close();
		super.onDestroy();
	}
	
}
