package com.ashandilya.popularmoviesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.MyViewHolder> {

    private String[] mDataset;

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_movies_list, parent, false);
                MyViewHolder vh = new MyViewHolder(v);
                return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    public MovieAdapter(String[] myDataset) {
        mDataset = myDataset;
    }
}
