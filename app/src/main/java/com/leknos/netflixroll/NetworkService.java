package com.leknos.netflixroll;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private Retrofit mRetrofit;

    public static NetworkService getInstance(){
        if(mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public TheMovieDbApi getJSONApi() {
        return mRetrofit.create(TheMovieDbApi.class);
    }

    //private class DataLoadedCallback implements Callback<>
}
