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

public class DisplayActivity extends Activity {

	final static int REQ_CODE = 1001;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		if( getIntent().getExtras() != null ) {
			String name = getIntent().getExtras().getString( "input" );
			
			TextView inputText = (TextView) findViewById( R.id.textView2 );
			inputText.setText( name );
		}
		
		Button backButton = (Button) findViewById( R.id.button2 );
		backButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		Button callCapitalize = (Button) findViewById( R.id.button1 );
		callCapitalize.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent( DisplayActivity.this, CapitalizeActivity.class );
				TextView toOutput = (TextView) findViewById( R.id.textView2 );
				String myOutput = toOutput.getText().toString();
				i.putExtra( "input2", myOutput ); 
				startActivityForResult( i, REQ_CODE );
			}
		});
	}
	
	
	public void onActivityResult( int requestCode, int resultCode, Intent data ) {
		super.onActivityResult(requestCode, resultCode, data);
		if( requestCode == REQ_CODE ) {
			if( resultCode == RESULT_OK && data.getExtras().containsKey( "input3" ) ) {
				TextView outputFinal = (TextView) findViewById( R.id.textView2 );
				outputFinal.setText( data.getExtras().getString( "input3" ) );
			}
		}
	}
}
