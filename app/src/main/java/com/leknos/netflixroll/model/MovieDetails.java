package com.leknos.netflixroll.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetails {
    private int id;
    private String title;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("vote_average")
    @Expose
    private double voteAverage;
    private String overview;

    public MovieDetails(int id, String title, String releaseDate, String backdropPath, double voteAverage, String overview, String posterPath) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
