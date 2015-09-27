package com.example.callevonanka.assignment_3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Calle Von Anka on 2015-09-15.
 */
public class QuoteFragment extends Fragment {

    ProgressBar mProgressbar;
    List<String> mQuoteList = new ArrayList<>();
    QuoteAdapter mQuoteAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("ONCREATE", "Fragment created");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("ONCREATEVIEW", "created view");
        View v = inflater.inflate(R.layout.fragment_quotes, container,false);

        mProgressbar = (ProgressBar) v.findViewById(R.id.quote_progress_bar);
        mProgressbar.setVisibility(View.GONE);

        ListView listView = (ListView) v.findViewById(R.id.quote_ListView);

        mQuoteAdapter = new QuoteAdapter(mQuoteList, getActivity().getLayoutInflater());
        listView.setAdapter(mQuoteAdapter);

        ImageButton faButton = (ImageButton) v.findViewById(R.id.fab);

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("QUOTEFRAGMENT", "on button click");
                mProgressbar.setVisibility(View.VISIBLE);
                try {
                    URL url = new URL("https://api.github.com/zen?access_token=0f892e365071c7e778a020e463d715b8ccb816f5");
                    new DownloadQuoteTask().execute(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }


    private class DownloadQuoteTask extends AsyncTask<URL, Void, String>{
        @Override
        protected String doInBackground(URL... params) {
            URL url = params[0];
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    //StringBuilder sb= new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine())!=null){
                        return line;
                    }
                }finally{
                    urlConnection.disconnect();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String line) {
                mProgressbar.setVisibility(View.GONE);
                mQuoteList.add(line);
                mQuoteAdapter.notifyDataSetChanged();
        }
    }
}
