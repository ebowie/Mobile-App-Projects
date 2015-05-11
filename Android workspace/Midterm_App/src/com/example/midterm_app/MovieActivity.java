package com.example.midterm_app;
/*
 * MidTerm: RottonTomatos app
 * File: MidTerm.zip
 * Name:Eric Bowers
 *
 */




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
import android.widget.ListView;

public class MovieActivity extends Activity {
    ProgressDialog progressDialog;
    Handler movie_handler;
    final int SHOW_PROGRESS = 0;
    final int DISMISS_PROGRESS = 1;
    ArrayList movieList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        movie_handler = new Handler(new Handler.Callback() {



            @Override
            public boolean handleMessage(Message msg) {
                // TODO Auto-generated method stub
                switch( msg.what ) {
                case SHOW_PROGRESS:
                    progressDialog = new ProgressDialog( MovieActivity.this );
                    progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
                    progressDialog.setCancelable( false );
                    progressDialog.setMessage("Loading Movies" );
                    progressDialog.show();
                    break;
                case DISMISS_PROGRESS:
                    progressDialog.dismiss();

                    break;

                }




                return false;
            }
        });


            GetAsyncTaskMovie getMovie = new GetAsyncTaskMovie(MovieActivity.this);
        String getMovieTab = getIntent().getStringExtra("movie");
        getMovie.handler = movie_handler;
       getMovie.execute(getMovieTab);








    }

    public void setData(final ArrayList<movieTabs> results){




  movieList = results;





        ListView listView = (ListView)findViewById(R.id.listView1);
        MainAdapter adapter = new MainAdapter(MovieActivity.this,movieList);
        listView.setAdapter( adapter );
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // TODO Auto-generated method stub
                intent = new Intent(MovieActivity.this,Movie2Activity.class);
                intent.putExtra("title", results.get(position).getTitle());
                intent.putExtra("thumbnail", results.get(position).getDetailedImage());
                intent.putExtra("date", results.get(position).getTheater());
                intent.putExtra("mpaa", results.get(position).getMpaaRating());
                intent.putExtra("runtime", results.get(position).getRuntime());
                intent.putExtra("audience", results.get(position).getAudience_score());
                intent.putExtra("critic", results.get(position).getCritic_score());
                intent.putExtra("alternate", results.get(position).getLinks());
                startActivity(intent);


            }

        });



    }

}
