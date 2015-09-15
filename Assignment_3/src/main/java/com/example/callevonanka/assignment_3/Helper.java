package com.example.callevonanka.assignment_3;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Calle Von Anka on 2015-09-15.
 */
public class Helper {
    //olika urler hämta

    public static URL getZenUrl

            private class DownloadCoverTask extends AsyncTask<URL, Void, Bitmap>{
                @Override
                protected Bitmap doInBackground(URL... params) {
                    URL url = params[0];
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try{
                        InputStream in =new BufferedInputStream(urlConnection.getInputStream());
                        return BitmapFactory.decodeStream(in);
                    }finally {
                        urlConnection.disconnect();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
}
