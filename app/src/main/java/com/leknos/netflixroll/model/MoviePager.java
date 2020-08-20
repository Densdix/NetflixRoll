package com.leknos.netflixroll.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.leknos.netflixroll.model.Movie;

import java.util.List;

public class MoviePager {
    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;

    public List<Movie> getMovies() {
        return movies;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
