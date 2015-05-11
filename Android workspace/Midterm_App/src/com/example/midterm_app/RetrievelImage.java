package com.example.midterm_app;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class RetrievelImage extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    int SHOW_PROGRESS = 0;
    int HIDE_PROGRESS = 1;
    Bitmap image;
    @Override
    protected Bitmap doInBackground(String... params) {
        // TODO Auto-generated method stub
        try {
            URL url = new URL( params[0] );
            image = BitmapFactory.decodeStream( url.openStream() );
        } catch ( Exception e ) {
            e.printStackTrace();

    }
        return image;

    }

    @Override
    protected void onPostExecute(Bitmap result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);


        imageView.setImageBitmap( image );
    }
}
