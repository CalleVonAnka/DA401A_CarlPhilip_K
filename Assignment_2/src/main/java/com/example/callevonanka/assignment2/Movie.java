package com.example.callevonanka.assignment2;


import android.graphics.drawable.Drawable;

/**
 * Created by Calle Von Anka on 2015-09-11.
 */
public class Movie {

    public String title;
    public String year;
    public String description;
    public int fan_art;
    public int cover;

    public Movie(String title, String year, String description, int fan_art, int cover) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.fan_art = fan_art;
        this.cover = cover;
    }
}
