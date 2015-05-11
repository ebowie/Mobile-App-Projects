package com.example.cnntechnologyarticles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Intent intent;
    Button news,likes,deleteLikes;
    private static DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DataManager(this);

       news = (Button)findViewById(R.id.button1);
       news.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            intent = new Intent(MainActivity.this, NewsActivity.class);
            intent.putExtra("type", 0);
            startActivity(intent);
        }
    });

       likes = (Button)findViewById(R.id.button2);
       likes.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            intent = new Intent(MainActivity.this, NewsActivity.class);
            intent.putExtra("type", 1);

            startActivity(intent);
        }
    });
       deleteLikes = (Button)findViewById(R.id.button3);
       deleteLikes.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Note note = new Note();










        }
    });

    }
}
