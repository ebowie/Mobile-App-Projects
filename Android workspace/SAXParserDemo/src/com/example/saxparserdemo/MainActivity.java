package com.example.saxparserdemo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new AsyncGetPerson().execute("http://liisp.uncc.edu/~mshehab/api/xml/person.xml");
		new AsyncGetPersons().execute("http://liisp.uncc.edu/~mshehab/api/xml/persons.xml");
	}
}
