package com.example.callevonanka.assignment2;


import android.graphics.drawable.Drawable;

/**
 * Created by Calle Von Anka on 2015-09-11.
 */
public class Movie {

    String title;
    String year;
    String description;
    Drawable fan_art;
    Drawable cover;

    public Movie(String title, String year, String description, Drawable fan_art, Drawable cover) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.fan_art = fan_art;
        this.cover = cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFan_art(Drawable fan_art) {
        this.fan_art = fan_art;
    }

    public void setCover(Drawable cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public Drawable getFan_art() {
        return fan_art;
    }

    public Drawable getCover() {
        return cover;
    }
}
