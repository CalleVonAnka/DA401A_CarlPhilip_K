package com.example.callevonanka.assignment_3;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by Calle Von Anka on 2015-09-15.
 */
public class MovieFragment {

    ProgressBar = mProgressbar;


    onPreExecue(){
        mProgressbar
    }

    onViewCreate
    new DownloadMovieTask().execute("url1", "url2, Helper.getMovies()")

    private class DownloadMovieTask extends AsyncTask<URL, Void, List<Movie>>{
        @Override
        protected List<movies> doInBackground(URL... params) {
            URL url = params[0];
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    return createListOfMovies(inputStream);
                }finally{
                    urlConnection.disconnect();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        private List<Movie> createListOfMovies(InputStream inputStream){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb= new StringBuilder();
            String line;
            while (line = bufferedReader.readLine()!=null){
                sb.append(line);
            }
            String s = sb.toString();
            Movie.fromJson(new JSONArray(s));
        }
    }

    onPostExecute(List<movie> movie){
        mProgessBar.setVisibility(View.GONE);
        mMovies.addAll(movies);
        mAdapter.notifyDataSetChanged();
    }
}
