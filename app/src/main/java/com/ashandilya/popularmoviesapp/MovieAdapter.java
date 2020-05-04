package com.ashandilya.popularmoviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.inflater = LayoutInflater.from(context);
        this.movies = movies;
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.popular_movies_list,parent,false);
            return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        holder.movieTitle.setText(movies.get(position).getMovieTitle());
        holder.movieOverview.setText(movies.get(position).getMovieOverview());
        holder.movieRating.setText(movies.get(position).getMovieRating());
        holder.movieReleaseDate.setText(movies.get(position).getMovieReleaseDate());
        Picasso.get().load(movies.get(position).getMovieThumbnail()).into(holder.movieThumbnail);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle, movieOverview, movieRating, movieReleaseDate;
        ImageView movieThumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieOverview = itemView.findViewById(R.id.movieOverview);
            movieRating = itemView.findViewById(R.id.movieRating);
            movieReleaseDate = itemView.findViewById(R.id.movieReleaseDate);

            movieThumbnail = itemView.findViewById(R.id.movieThumbnail);


        }
    }
}
