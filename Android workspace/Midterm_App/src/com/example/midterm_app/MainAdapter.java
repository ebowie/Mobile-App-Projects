package com.example.midterm_app;



import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<movieTabs>  {
    Context context;
    ArrayList<movieTabs> objects;
    public MainAdapter(Context context, ArrayList<movieTabs> objects) {
        super(context, R.layout.listview, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View listView = inflater.inflate( R.layout.listview, parent, false );

        TextView movieTitle = (TextView) listView.findViewById( R.id.title );
        TextView year = (TextView) listView.findViewById( R.id.date);
           movieTitle.setText( objects.get( position ).getTitle());
        year.setText( "Year: "+objects.get( position ).getYear() + "    " + "Rating: "+objects.get(position).getMpaaRating()  );

        ImageView imageView = (ImageView) listView.findViewById( R.id.thumbImage);
        RetrievelImage nextPhoto = new RetrievelImage();
        nextPhoto.imageView = imageView;
        nextPhoto.execute(objects.get(position).getThumbnail());

        return listView;


    }
}
