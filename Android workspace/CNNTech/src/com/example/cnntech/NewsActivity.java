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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NewsActivity extends Activity {
	
	String URL = "http://rss.cnn.com/rss/cnn_tech.rss";
	ArrayList<Entry> allEntries = new ArrayList<Entry>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		new AsyncXMLRetrieval(this).execute( URL );
	}
	
	public void setupData(ArrayList<Entry> result){
		allEntries = result;
		ListView listView = (ListView) findViewById( R.id.listView1 );
		ArrayAdapter<Entry> adapter = new ArrayAdapter<Entry>( this, android.R.layout.simple_list_item_1, android.R.id.text1, allEntries );
		listView.setAdapter( adapter );
	
		
		listView.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.d( "demo", "Item " + arg2 + " was clicked." );
				
				Intent i = new Intent( NewsActivity.this, PreviewActivity.class );
				Entry temp = new Entry();
				temp = allEntries.get( arg2 );
				i.putExtra( "URL", temp.getUrl() );
				// Toast.makeText( this, "Presend: " + temp.getUrl(), Toast.LENGTH_LONG ).show();
				startActivity( i );
				
			}
			
			
		});
		
	}
	
	
}
