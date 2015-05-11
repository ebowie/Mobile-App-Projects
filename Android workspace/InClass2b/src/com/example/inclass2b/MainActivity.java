/*
 * Assignment: In-Class 2
 * Full Name: David Farynyk and Eric Bowers 
 * File Name: InClass2a
 * Class: ITCS 5180, Summer I
 * Date: 5/29/2014
 */

package com.example.inclass2b;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class MainActivity extends Activity implements View.OnClickListener {
	LinearLayout root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RadioButton b = (RadioButton)findViewById(R.id.radio0);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.BLACK);
		
		b = (RadioButton)findViewById(R.id.radio1);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.WHITE);
		
		b = (RadioButton)findViewById(R.id.radio2);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.RED);
		
		b = (RadioButton)findViewById(R.id.radio3);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.GREEN);
		
		b = (RadioButton)findViewById(R.id.radio4);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.BLUE);
		
		b = (RadioButton)findViewById(R.id.radio0);
		b.setOnClickListener(this);
		b.setTag(R.id.tag_key_color, Color.BLACK);
		
		root = (LinearLayout) findViewById( R.id.LinearLayout1 );
		
		SeekBar Alpha = (SeekBar)findViewById(R.id.seekBar1);
		Alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				root.setAlpha( (float)( progress / 100.0 ) );
			}
		});
		
		
	}
	
	public void onClick(View v) {
		int color = ((Integer)v.getTag( R.id.tag_key_color )).intValue();
		root.setBackgroundColor( color );
	}
	
	
}
