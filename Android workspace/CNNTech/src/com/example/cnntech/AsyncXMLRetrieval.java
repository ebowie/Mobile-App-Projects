package com.example.cnntech;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Handler;

public class AsyncXMLRetrieval extends AsyncTask <String, Void, ArrayList<Entry> > {

	Handler returnInput;

	NewsActivity activity;
	public AsyncXMLRetrieval(NewsActivity activity) {
		this.activity = activity;
	}

	@Override
	protected ArrayList<Entry> doInBackground(String... params) {

		try{
			URL url = new URL( params[0] );
			HttpURLConnection con = ( HttpURLConnection ) url.openConnection();
			con.connect();
			int statusCode = con.getResponseCode();
			if( statusCode == HttpURLConnection.HTTP_OK ) {
				InputStream in = con.getInputStream();
				return ParseCNN.ParseAnXML.parsePosts( in );
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
	protected void onPostExecute(ArrayList<Entry> result) {
		super.onPostExecute(result);
		activity.setupData(result);

	}








}
