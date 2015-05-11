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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static Button exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditText inputText = (EditText) findViewById( R.id.editText1 );
		inputText.addTextChangedListener( new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				TextView charCount = (TextView) findViewById( R.id.textView2 );
				charCount.setText( "Number of characters entered: " + s.toString().length() );
				
				Button myButton = (Button) findViewById( R.id.button1 );
				if( s.toString().length() == 0 ) {
					myButton.setEnabled( false );
				}
				else {
					myButton.setEnabled( true );
				}
			}
			
		});
		
		Button nextButton = (Button) findViewById( R.id.button1 );
		nextButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent( MainActivity.this, DisplayActivity.class );
				EditText currText = (EditText) findViewById( R.id.editText1 );
				String toOutput = currText.getText().toString();
				i.putExtra( "input", toOutput ); 
				startActivity( i );
			}
		});

		
		exit();
	}
	
	public final void next() {
		
	}
	
	public final void exit() {
		
		exit = (Button) findViewById( R.id.button2 );
		exit.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
}
