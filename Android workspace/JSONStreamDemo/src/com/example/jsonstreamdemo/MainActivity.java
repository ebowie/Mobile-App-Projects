package com.example.jsonstreamdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new JSONGetAsyncTask().execute("http://liisp.uncc.edu/~mshehab/api/json/persons.json");
	}
}
