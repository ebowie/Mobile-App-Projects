package com.example.cnntechnologyarticles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class PreviewActivity extends Activity {
    private static DataManager dm;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        dm = new DataManager(this);

        final String link1 = getIntent().getStringExtra("link");
        final String title = getIntent().getStringExtra("title");
        final int type = getIntent().getIntExtra("type", 0);
        WebView web = (WebView)findViewById(R.id.webView1);

        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(link1);
        web.setWebViewClient(new WebViewClient());

        Button addLike = (Button)findViewById(R.id.button1);
        if(type == 1) {
            addLike.setEnabled(false);
        }

        addLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Note note = new Note();
                note.setSubject(title);
                note.setText(link1);
                dm.saveNote(note);

            }
        });


    }
    @Override
    protected void onDestroy(){
        dm.close();
        super.onDestroy();
    }
}
