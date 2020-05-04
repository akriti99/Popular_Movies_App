package com.ashandilya.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MovieAdapter movieAdapter;
    List<Movie> movies;
    private static String movieDb = "https://api.themoviedb.org/3/discover/movie?api_key=d490111bf870ffef1ad5b1519fc927ca";
    private Object Tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setHasFixedSize(true);
        movies = new ArrayList<>();
        showMovies();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
        //MovieAdapter = (Adapter) new MovieAdapter(this, movies);
        //recyclerView.setAdapter((RecyclerView.Adapter) MovieAdapter);

    }

    private void showMovies() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, movieDb, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject movieObject = response.getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setMovieTitle(movieObject.getString("title").toString());
                        movie.setMovieOverview(movieObject.getString("overview").toString());
                        movie.setMovieRating(movieObject.getString("vote_average").toString());
                        movie.setMovieReleaseDate(movieObject.getString("release_date").toString());
                        movie.setMovieThumbnail(movieObject.getString("poster_path").toString());

                        movies.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d((String) Tag, "onErrorResponse: " + error.getMessage());
            }
        });
    }
}
