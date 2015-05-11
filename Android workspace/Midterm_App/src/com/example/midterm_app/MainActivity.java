package com.example.midterm_app;
/*
 * MidTerm: RottonTomatos app
 * File: MidTerm.zip
 * Name:Eric Bowers
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    ListView listView;
    final String [] list = {"Box Office Movies","In Theaters Movies","Opening Movies","Upcoming Movies"};
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // TODO Auto-generated method stub
                 intent = new Intent(MainActivity.this, MovieActivity.class);

                if(position == 0) {
                    intent.putExtra("movie", "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=4a93dg4sfy7n27p9bsg2pdww&limit=20");

                }else if(position == 1) {
                   intent.putExtra("movie", "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=4a93dg4sfy7n27p9bsg2pdww&page_limit=20");
                }else if(position == 2) {
                    intent.putExtra("movie","http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey=4a93dg4sfy7n27p9bsg2pdww&limit=20");
                }else if(position == 3){
                    intent.putExtra("movie", "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=4a93dg4sfy7n27p9bsg2pdww&page_limit=20");

                }
                startActivity(intent);

            }
        });





    }
}
