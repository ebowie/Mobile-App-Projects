package com.example.fifaworlcupphotos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoActivity extends Activity {
    TextView Title,Description;
    final int SHOW_PROGRESS = 0;
    final int SHOW_DISMISS = 1;
    ImageView Image;
    ProgressDialog progressDialog;
    Handler handlerPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        String title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("image");
        String decription= getIntent().getStringExtra("description");
       handlerPhoto = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch(msg.what){
            case SHOW_PROGRESS:
                progressDialog = new ProgressDialog(PhotoActivity.this);
                progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading Photo");
                progressDialog.show();

                break;

            case SHOW_DISMISS:
                progressDialog.dismiss();
            }

            return false;
        }
    }) ;



        Title = (TextView)findViewById(R.id.textView1);
        Title.setText(title);

        Image = (ImageView)findViewById(R.id.imageView1);
        getXMlPhoto getPhoto = new getXMlPhoto();
        getPhoto.imageView = Image;
        getPhoto.handler = handlerPhoto;
        getPhoto.execute(image);

        Description =(TextView)findViewById(R.id.textView2);
        Description.setText(decription);


    }
}
