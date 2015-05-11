package com.example.midterm_app;
/*
 * MidTerm: RottonTomatos app
 * File: MidTerm.zip
 * Name:Eric Bowers
 *
 */
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





public class movieUtils {

    static public class MovieJSONParser{
        static ArrayList<movieTabs> parse(String in) throws JSONException{
            ArrayList<movieTabs> moviesList = new ArrayList<movieTabs>();

            JSONObject root = new JSONObject(in);
            JSONArray moviesJSONArray = root.getJSONArray("movies");


            for(int i =0; i<moviesJSONArray.length(); i++) {
                JSONObject movieJSONObject =  moviesJSONArray.getJSONObject(i);

                movieTabs movie = new movieTabs();
                //{"id":"771355226","title":"22 Jump Street","year":2014,"mpaa_rating":"R"

                movie.setTitle(movieJSONObject.getString("title"));
                movie.setId(movieJSONObject.getInt("id"));
                movie.setYear(movieJSONObject.getInt("year"));
                movie.setMpaaRating(movieJSONObject.getString("mpaa_rating"));
                movie.setRuntime(movieJSONObject.getInt("runtime"));

                JSONObject businessObject = movieJSONObject.getJSONObject("posters");
                movie.setThumbnail(businessObject.getString("thumbnail"));
                movie.setDetailedImage(businessObject.getString("detailed"));

                JSONObject releaseDate = movieJSONObject.getJSONObject("release_dates");
                movie.setTheater(releaseDate.getString("theater"));

                JSONObject rating = movieJSONObject.getJSONObject("ratings");
                movie.setAudience_score(rating.getInt("audience_score"));
                movie.setCritic_score(rating.getInt("critics_score"));

                JSONObject link = movieJSONObject.getJSONObject("links");
                movie.setLinks(link.getString("alternate"));


                moviesList.add(movie);

            }

            return moviesList;
    }

    }

}
