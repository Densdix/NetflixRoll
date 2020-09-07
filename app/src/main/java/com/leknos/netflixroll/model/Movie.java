package com.leknos.netflixroll.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table")
public class Movie {

    @PrimaryKey
    private int id;

    private String title;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    private String textData;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    @Expose
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTextData() {
        return textData;
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
