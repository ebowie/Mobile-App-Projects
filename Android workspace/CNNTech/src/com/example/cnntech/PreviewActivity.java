/*
 * Assignment: In Class 5
 * File Name: InClassAssignment6.zip
 * Students: David Farynyk and Eric Bowers
 */

package com.example.cnntech;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class PreviewActivity extends Activity {
	
	String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		
		
		if( getIntent().getExtras() != null ) {
			data = getIntent().getExtras().getString( "URL" );
		}
		
		// Toast.makeText( this, data, Toast.LENGTH_LONG ).show();
		
		WebView content = (WebView) findViewById( R.id.webView1 );
		content.loadUrl( data );
		
		
		
		Button clickButton = (Button) findViewById( R.id.button1 );
		clickButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// dm.saveNote();
				
			}
		});
	}
}
