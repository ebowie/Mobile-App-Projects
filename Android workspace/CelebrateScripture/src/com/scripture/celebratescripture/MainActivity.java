package com.scripture.celebratescripture;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	Handler mainUI;
	ProgressDialog progressDialog;
	final int PROGRESS_DISPLAY = 0;
	final int PROGRESS_HIDE = 1;
	ArrayList<BlogPost> allPosts;
	ExecutorService threadPool;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Instantiate the ArrayList to hold all Blog Post data
		allPosts = new ArrayList<BlogPost>();
		
		mainUI = new Handler( new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				switch( msg.what ) {
					case PROGRESS_DISPLAY:
						progressDialog = new ProgressDialog( MainActivity.this );
						progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
						progressDialog.setCancelable( false );
						progressDialog.setMessage( "Progress of Loop" );
						progressDialog.show();
						break;
					case PROGRESS_HIDE:
						progressDialog.hide();
						allPosts = ( ArrayList<BlogPost> ) msg.obj;
						Log.d( "demo", "Total blog posts: " + allPosts.size() );
						Log.d( "demo", allPosts.toString() );
						
						ListView scrollPassages = (ListView) findViewById( R.id.listView1 );
						MainAdapter adapter = new MainAdapter( MainActivity.this, allPosts );
						scrollPassages.setAdapter( adapter );
						scrollPassages.setOnItemClickListener( new OnItemClickListener () {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								Log.d( "demo", "Item " + arg2 + " was clicked." );
								
							}
							
						});
						
						break;
				}
				
				return false;
			}
		});
		
		Log.d( "demo", "Launching Celebrate Scripture" );
		AsyncXMLRetrieval loadXML = new AsyncXMLRetrieval();
		loadXML.fromMainUI = mainUI;
		loadXML.execute( "http://www.celebratescripture.com/feeds/posts/default" );
		
	}
}
