package com.example.jsonstreamdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

import org.apache.commons.io.IOUtils;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

public class JSONGetAsyncTask extends
		AsyncTask<String, Void, LinkedList<Person>> {

	@Override
	protected LinkedList<Person> doInBackground(String... params) {
		try {
			URL url = new URL(params[0]);
			URLConnection urlConnection = url.openConnection();
			InputStream in = urlConnection.getInputStream();

			//String text = IOUtils.toString(in);
			//Log.d("demo", text);
			
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			/*
			 * [
			 * {"name":"Bob Smith","id":800111111,"age":55,"department":"SIS"},
			 * {"name":"Alice Green","id":80022222,"age":25,"department":"CS"},
			 * {"name":"Kamal Ali","id":80033333,"age":45,"department":"ECE"} ]
			 */

			LinkedList<Person> persons = new LinkedList<Person>();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				Person person = new Person();
				while (reader.hasNext()) {
					String name = reader.nextName();
					if (name.equals("name")) {
						person.setName(reader.nextString());
					} else if (name.equals("id")) {
						person.setId(reader.nextInt() + "");
					} else if (name.equals("age")) {
						person.setAge(reader.nextInt() + "");
					} else {
						reader.skipValue();
					}
				}
				persons.add(person);
				reader.endObject();
			}
			reader.endArray();
			reader.close();
			return persons;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(LinkedList<Person> result) {
		super.onPostExecute(result);
		if (result != null) {
			Log.d("demo", result.toString());
		}
	}
}
