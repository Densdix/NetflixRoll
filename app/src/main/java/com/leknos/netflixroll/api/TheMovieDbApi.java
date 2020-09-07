package com.leknos.netflixroll.api;

import com.leknos.netflixroll.model.MovieDetails;
import com.leknos.netflixroll.model.MoviePager;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.leknos.netflixroll.util.Constants.API_KEY_VALUE;
import static com.leknos.netflixroll.util.Constants.LANGUAGE;

public interface TheMovieDbApi {
    @GET("search/movie?api_key="+API_KEY_VALUE+"&language="+LANGUAGE)
    Call<MoviePager> getMovies(@Query("query") String query);

    @GET("movie/{movie_id}?api_key="+API_KEY_VALUE+"&language="+LANGUAGE)
    Call<MovieDetails> getDetailMovie(@Path("movie_id") String var);


}
