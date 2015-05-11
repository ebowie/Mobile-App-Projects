package com.example.fifaworlcupphotos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class getXMlPhoto extends AsyncTask<String, Void, Bitmap> {
    Bitmap bm;
    ImageView imageView;
    Handler handler;
    Message msg = new Message();
    int SHOW_PROGRESS = 0;
    int SHOW_DISMISS = 1;

    @Override
    protected Bitmap doInBackground(String... params) {
        // TODO Auto-generated method stub
        try {
            URL url = new URL(params[0]);
            bm = BitmapFactory.decodeStream(url.openStream());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return bm;
    }
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        msg = new Message();
        msg.what = SHOW_PROGRESS;
        handler.sendMessage(msg);
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        imageView.setImageBitmap(bm);


        msg = new Message();
        msg.what = SHOW_DISMISS;
        handler.sendMessage(msg);
    }

}
