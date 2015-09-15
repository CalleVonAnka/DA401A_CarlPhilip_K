package com.example.callevonanka.assignment2;


import android.support.annotation.Nullable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MovieItemDetail extends Fragment {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_item_detail,container, false);

        Bundle movie = getArguments();

        ImageView cover = (ImageView) view.findViewById(R.id.movie_cover);
        cover.setImageResource(movie.getInt("movie_fanart"));

        TextView title = (TextView) view.findViewById(R.id.movie_title);
        title.setText(movie.getString("movie_title"));

        TextView year = (TextView) view.findViewById(R.id.movie_year);
        year.setText(movie.getString("movie_year"));

        TextView description = (TextView) view.findViewById(R.id.movie_description);
        description.setText(movie.getString("movie_description"));

        return view;
    }
}
