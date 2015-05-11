package com.example.fifaworlcupphotos;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
    ArrayList<Article> title = new ArrayList<Article>();
    ProgressDialog progressDialog;
    final int PROGRESS_DIALOG =0;
    final int PROGRESS_DISMISS =1;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        handler = new Handler(new Handler.Callback(){

            @Override
            public boolean handleMessage(Message msg) {
                // TODO Auto-generated method stub
                switch(msg.what){
                case PROGRESS_DIALOG:
                    progressDialog = new ProgressDialog( MainActivity.this );
                    progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
                    progressDialog.setCancelable( false );
                    progressDialog.setMessage("Loading titles");
                    progressDialog.show();
                    break;
                case PROGRESS_DISMISS:
                    progressDialog.dismiss();
                    break;
                }


                return false;
            }

        });



        parseSAXxml articleTitle = new parseSAXxml(MainActivity.this);
        articleTitle.handler2 = handler;
       articleTitle.execute("http://www.fifa.com/newscentre/photo/rss.xml");



          }

    public void setData(ArrayList<Article> result){
        title = result;
        ListView listView = (ListView)findViewById(R.id.listView1);
        final ArrayAdapter<Article> adapter = new ArrayAdapter<Article>(this,android.R.layout.simple_list_item_1,android.R.id.text1,title);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener( new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(MainActivity.this,PhotoActivity.class);
                intent.putExtra("title", title.get(position).getTitle());
                intent.putExtra("image", title.get(position).getImage());
                intent.putExtra("description", title.get(position).getDescription());
                startActivity(intent);



            }

        });
    }
}
