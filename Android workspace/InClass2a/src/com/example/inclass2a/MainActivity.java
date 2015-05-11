/*
 * Assignment: In-Class 2
 * Full Name: David Farynyk and Eric Bowers 
 * File Name: InClass2a
 * Class: ITCS 5180, Summer I
 * Date: 5/29/2014
 */

package com.example.inclass2a;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements View.OnClickListener {
	LinearLayout root;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.BLACK);
		
		b = (Button)findViewById(R.id.button2);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.WHITE);
		
		b = (Button)findViewById(R.id.button3);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.RED);
		
		b = (Button)findViewById(R.id.button4);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.GREEN);
		
		b = (Button)findViewById(R.id.button5);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.BLUE);
		
		root = (LinearLayout) findViewById( R.id.LinearLayout1 );
	}
	
	public void onClick(View v) {
		int color = ((Integer)v.getTag( R.id.tag_key_color )).intValue();
		root.setBackgroundColor( color );
	}
}
