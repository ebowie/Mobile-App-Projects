package com.example.fifaworldcupphotos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d( "test", "Application launched" );
		new RetrieveXML().execute( "http://www.fifa.com/newscentre/photo/rss.xml" );
		
	}
}
