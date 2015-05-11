package com.example.jsondemo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new PersonsGetAsyncTask().execute("http://liisp.uncc.edu/~mshehab/api/json/persons.json");
	}
}
