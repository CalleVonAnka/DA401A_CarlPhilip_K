package com.example.callevonanka.assignment2;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MovieListFrag extends Fragment {

    ArrayList<Movie> mMovieList = new ArrayList<>();

    public MovieListFrag() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MOVIELIST FRAG", "Created");

        TypedArray movies = getResources().obtainTypedArray(R.array.movies);

        for (int i = 0; i < movies.length(); i++){
            TypedArray movieArray = getResources().obtainTypedArray(movies.getResourceId(i,0));
            Movie movie = new Movie(movieArray.getString(0), movieArray.getString(1),
                    movieArray.getString(2), movieArray.getResourceId(3, 0), movieArray.getResourceId(4, 0));
            mMovieList.add(movie);
            movieArray.recycle();
        }
        movies.recycle();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_list_frag, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.movieList_Layout);
        gridView.setAdapter(new Adapter(mMovieList, getActivity().getLayoutInflater()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


                Movie movie = mMovieList.get(position);

                Bundle args = new Bundle();
                args.putString("movie_title", movie.title);
                args.putString("movie_year", movie.year);
                args.putString("movie_description", movie.description);
                args.putInt("movie_fanart", movie.fan_art);

                Fragment movieFrag = new MovieItemDetail();
                movieFrag.setArguments(args);

                getActivity()
                        .getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Main_Activity_ID, movieFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}
