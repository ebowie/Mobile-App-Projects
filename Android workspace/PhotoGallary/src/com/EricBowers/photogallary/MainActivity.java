package com.EricBowers.photogallary;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    ProgressDialog progressDialog;
    Intent intent;
    String Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this,PhotoActivity.class);
        new DoWork().execute("http://liisp.uncc.edu/~mshehab/api/photos.txt");



        photo();
        slideShow();



    }



    class DoWork extends AsyncTask<String, Void, String> {

        String transport;

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String results = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statusCode = con.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    InputStream in = con.getInputStream();
                    String text = IOUtils.toString(in);
                  results = text;

                }else {
                    Log.d("demo", "Did not enter if statement");
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }




            return results;
        }



        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Retrieving image URL's");
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);


            intent.putExtra("URL",result);


            //System.out.println(result);

            progressDialog.dismiss();
        }





    }




    public void photo(){




       findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            intent.putExtra("counter", "0");

            startActivity(intent);
        }
    });

   }

    public void slideShow() {
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                intent.putExtra("counter", "1");

                startActivity(intent);

            }
        });
    }



}