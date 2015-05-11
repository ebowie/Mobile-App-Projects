package com.example.midterm_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Movie2Activity extends Activity {

    TextView Title, Date,Runtime,Mpaa,Audience,Critic,audiencePercent,criticPercent;
    ImageView poster,web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie2);
        String title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("thumbnail");
        String date = getIntent().getStringExtra("date");
        String mpaa = getIntent().getStringExtra("mpaa");
        int runtime = getIntent().getIntExtra("runtime", 100);
        int critics = getIntent().getIntExtra("critic", 0);
        int audience = getIntent().getIntExtra("audience", 0);
        int hr =0;
        int min =0;
        final String webb = getIntent().getStringExtra("alternate");

        Title = (TextView)findViewById(R.id.textView1);
        Title.setText(title);

        poster = (ImageView)findViewById(R.id.imageView1);
        RetrievelImage nextPhoto = new RetrievelImage();
        nextPhoto.imageView = poster;
        nextPhoto.execute(image);

        Date = (TextView)findViewById(R.id.textView2);
        Date.setText(date);

        Mpaa = (TextView)findViewById(R.id.textView3);
        Mpaa.setText("Rating:"+mpaa);

        Runtime = (TextView)findViewById(R.id.textView4);
        hr = runtime/60;
        min = runtime%60;
        Runtime.setText(hr +":Hour"+" "+min+":Mins");

        Audience = (TextView)findViewById(R.id.textView5);
        Audience.setText("Audience");

        Critic = (TextView)findViewById(R.id.textView6);
        Critic.setText("Critics");

        audiencePercent = (TextView)findViewById(R.id.textView7);
        audiencePercent.setText(audience +"%");

        criticPercent = (TextView)findViewById(R.id.textView8);
        criticPercent.setText(critics+"%");

        web = (ImageView)findViewById(R.id.imageView2);
        web.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(webb));
                       startActivity(intent);
            }
        });


    }
}
