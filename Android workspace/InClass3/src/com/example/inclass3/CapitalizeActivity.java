/*
 * Assignment: In Class 3
 * File Name: HW3.zip
 * Names: Eric Bowers and David Farynyk
 * Group: H
 */


package com.example.inclass3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CapitalizeActivity extends Activity {

	private String myInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capitalize);
		
		
		if( getIntent().getExtras() != null ) {
			myInput = getIntent().getExtras().getString( "input2" );
		}
		
		Button returnText = (Button) findViewById( R.id.button1 );
		returnText.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra( "input3", myInput.toUpperCase() );
				setResult( RESULT_OK, i );
				finish();
			}
		});
		
	}
}
