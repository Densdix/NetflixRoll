package com.leknos.netflixroll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDbApi {
    String API_KEY = "862c044119db5df703d9b0d454af8251";
    String LANGUAGE = "en-US";

    @GET("search/movie?api_key="+API_KEY+"&language="+LANGUAGE)
    Call<MoviePager> getMovies(@Query("query") String query);
}
