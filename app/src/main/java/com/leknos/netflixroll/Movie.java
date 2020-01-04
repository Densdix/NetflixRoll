package com.leknos.netflixroll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Movie {
    private int id;
    private String title;
    private String textData;
    private SimpleDateFormat releaseDate;
    private String posterPath;
    private double voteAverage;
    private String overview;

    public Movie(int id, String title, String textData, String posterPath, double voteAverage, String overview) {
        this.id = id;
        this.title = title;
        this.textData = textData;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public SimpleDateFormat convertStringToData(){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTextData() {
        return textData;
    }

    public SimpleDateFormat getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }
}
