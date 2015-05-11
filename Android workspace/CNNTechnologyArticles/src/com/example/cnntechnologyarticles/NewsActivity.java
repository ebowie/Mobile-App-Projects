package com.example.cnntechnologyarticles;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsActivity extends Activity {
    private static DataManager dm;
    ArrayList<CNN> allEntries = new ArrayList<CNN>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        dm = new DataManager(this);
       int type = getIntent().getIntExtra("type", 0);

        if(type == 0){
            new RetrievieXML(this).execute("http://rss.cnn.com/rss/cnn_tech.rss");
        }else
        {
            final List<Note> notes = dm.getAllNotes();
            ListView listView = (ListView)findViewById(R.id.listView1);
            ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,android.R.layout.simple_list_item_1,android.R.id.text1,notes);
           listView.setAdapter(adapter);
           listView.setOnItemClickListener(new OnItemClickListener(){

               @Override
               public void onItemClick(AdapterView<?> parent, View view,
                       int position, long id) {
                   // TODO Auto-generated method stub
                   Intent intent = new Intent(NewsActivity.this, PreviewActivity.class);
                   intent.putExtra("link", notes.get(position).getText());
                   intent.putExtra("title", notes.get(position).getSubject());
                   intent.putExtra("type", 1);
                   startActivity(intent);

               }

           });

        }




    }

    public void setData(ArrayList<CNN> result) {
        //ListView
        allEntries = result;
        ListView listView = (ListView)findViewById(R.id.listView1);
         ArrayAdapter<CNN> adapter = new ArrayAdapter<CNN>(this,android.R.layout.simple_list_item_1,android.R.id.text1,allEntries);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(NewsActivity.this, PreviewActivity.class);
                intent.putExtra("link", allEntries.get(position).getLink());
                intent.putExtra("title", allEntries.get(position).getTitle());
                intent.putExtra("type", 0);
                startActivity(intent);

            }

        });
    }
    @Override
    public void onDestroy(){
        dm.close();
        super.onDestroy();
    }
}
