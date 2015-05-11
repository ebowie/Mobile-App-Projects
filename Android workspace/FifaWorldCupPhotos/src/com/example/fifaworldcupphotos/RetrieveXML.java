package com.example.fifaworldcupphotos;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class RetrieveXML extends AsyncTask<String, Void, ArrayList<Article> > {
	
	@Override
	protected ArrayList<Article> doInBackground(String... params) {
		
		try {
			URL url = new URL( params[0] );
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			int statusCode = con.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream in = con.getInputStream();
				//String text = IOUtils.toString(in);
				//Log.d("demo", text);
				return ArticleUtils.ArticleSAXParser.parseArticles(in);
			}
		} catch ( MalformedURLException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Article> result) {
		super.onPostExecute(result);
		
		// Log.d("result", result.toString() );
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}
	
	
	
}
