package com.example.callevonanka.assignment2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Calle Von Anka on 2015-09-14.
 */
public class Adapter extends BaseAdapter {

    List<Movie> mMovieList;
    LayoutInflater mLayoutInflater;

    public Adapter(List<Movie> mMovieList, LayoutInflater mLayoutInflater) {
        this.mMovieList = mMovieList;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("ADAPTER", "getView");
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.movie_item, parent, false);
        }
        Movie movie = (Movie) getItem(position);

        ImageView coverImageView = (ImageView) convertView.findViewById(R.id.movie_cover);
        coverImageView.setImageResource(movie.cover);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.movie_title);
        titleTextView.setText(movie.title);

        TextView yearTextView = (TextView) convertView.findViewById(R.id.movie_year);
        yearTextView.setText(movie.year);

        return convertView;
    }
}
