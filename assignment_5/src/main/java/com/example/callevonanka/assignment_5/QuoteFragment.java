package com.example.callevonanka.assignment_5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
 * Created by Calle Von Anka on 2015-11-02.
 */
public class QuoteFragment extends Fragment {

    ProgressBar mProgressbar;
    List<String> mQuoteList = new ArrayList<>();
    QuoteAdapter mQuoteAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("ONCREATE", "Fragment created");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("ONCREATEVIEW", "created view");
        View v = inflater.inflate(R.layout.fragment_quotes, container,false);

        mProgressbar = (ProgressBar) v.findViewById(R.id.quote_progress_bar);
        mProgressbar.setVisibility(View.GONE);

        final ListView listView = (ListView) v.findViewById(R.id.quote_ListView);

        mQuoteAdapter = new QuoteAdapter(getContext(), R.layout.fragment_quotes, mQuoteList);
        listView.setAdapter(mQuoteAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.delete_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                mode.setTitle("Selected");
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                Log.i("listview clicked", "yes");
                switch (item.getItemId()) {
                    case R.id.delete_id:
                        SparseBooleanArray selectedId = mQuoteAdapter.getSelectedIds();
                        for (int i = (selectedId.size() - 1); i >= 0; i--) {
                            if (selectedId.valueAt(i)) {
                                String selected = mQuoteAdapter.getItem(selectedId.keyAt(i));
                                mQuoteAdapter.remove(selected);
                            }
                        }
                        mode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {
                mQuoteAdapter.removeSelection();
            }

            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                final int count = listView.getCheckedItemCount();
                mode.setTitle(count + " selected");
                mQuoteAdapter.toggleSelection(position);
            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("menu clicked", "yes");
        switch (item.getItemId()){
            case R.id.get_quote:
                mProgressbar.setVisibility(View.VISIBLE);
                try {
                    URL url = new URL("https://api.github.com/zen?access_token=0f892e365071c7e778a020e463d715b8ccb816f5");
                    new DownloadQuoteTask().execute(url);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return false;
    }

    private class DownloadQuoteTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... params) {
            URL url = params[0];
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

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
            super.onPostExecute(line);
            mProgressbar.setVisibility(View.GONE);
            mQuoteList.add(line);
            mQuoteAdapter.notifyDataSetChanged();
        }
    }
}
