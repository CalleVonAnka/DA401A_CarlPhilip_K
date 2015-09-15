package com.example.callevonanka.assignment_3;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Calle Von Anka on 2015-09-15.
 */
public class Movie {

    public String title;
    public String year;
    public String coverUrl;

    public static Movie fromJson(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();
        movie.title = jsonObject.getString("title");
        movie.year = jsonObject.getString("year");
        movie.coverUrl = jsonObject.getString("cover");
        return movie;
    }

}
