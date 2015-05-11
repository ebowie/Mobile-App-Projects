package com.scripture.celebratescripture;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncXMLRetrieval extends AsyncTask<String, Void, ArrayList<BlogPost> > {
	
	Handler fromMainUI;
	Message msg;
	int PROGRESS_DISPLAY = 0;
	int PROGRESS_HIDE = 1;
	
	@Override
	protected ArrayList<BlogPost> doInBackground( String... input ) {
		
		try{
			URL url = new URL( input[0] );
			HttpURLConnection con = ( HttpURLConnection ) url.openConnection();
			con.connect();int statusCode = con.getResponseCode();
			if( statusCode == HttpURLConnection.HTTP_OK ) {
				InputStream in = con.getInputStream();
				return SAXParseXML.ParseAnXML.parsePosts( in );
			}
			
		} catch ( MalformedURLException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		} catch ( SAXException e ) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(ArrayList<BlogPost> result) {
		super.onPostExecute(result);
		
		Log.d("result", "Result: " + result.toString() );
		msg = new Message();
		msg.what = PROGRESS_HIDE;
		msg.obj = result;
		fromMainUI.sendMessage( msg );
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		msg = new Message();
		msg.what = PROGRESS_DISPLAY;
		fromMainUI.sendMessage( msg );

	}
	
}
