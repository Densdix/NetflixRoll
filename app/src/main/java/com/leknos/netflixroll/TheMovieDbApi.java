package com.leknos.netflixroll;

import com.leknos.netflixroll.model.MoviePager;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.leknos.netflixroll.utils.Constants.API_KEY_VALUE;
import static com.leknos.netflixroll.utils.Constants.LANGUAGE;

public interface TheMovieDbApi {
    @GET("search/movie?api_key="+API_KEY_VALUE+"&language="+LANGUAGE)
    Call<MoviePager> getMovies(@Query("query") String query);
}
