package com.scripture.celebratescripture;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<BlogPost> {
	Context context;
	ArrayList<BlogPost> objects;
	
	public MainAdapter( Context context, ArrayList<BlogPost> objects ) {
		super( context, R.layout.home_array_adapter, objects );
		this.context = context;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View homeArrayView = inflater.inflate( R.layout.home_array_adapter, parent, false );
		
		TextView passagePerson = (TextView) homeArrayView.findViewById( R.id.textView1 );
		TextView details = (TextView) homeArrayView.findViewById( R.id.textView2 );
		passagePerson.setText( objects.get( position ).getPassage() + " by " + objects.get( position ).getPerson() );
		details.setText( objects.get( position ).getDate() + " - " + objects.get( position ).getTime() );
		
		ImageView imageView = (ImageView) homeArrayView.findViewById( R.id.imageView1 );
		
		HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
		// nextPhoto.handler = photoHandler;
		nextPhoto.imageView = imageView;
		nextPhoto.execute( objects.get( position ).getThumbnail() );
		
		// return super.getView(position, convertView, parent);
		return homeArrayView;
	}
	
	
	
	
	
}
