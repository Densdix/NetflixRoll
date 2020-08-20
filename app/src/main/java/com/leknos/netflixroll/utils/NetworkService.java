package com.leknos.netflixroll.utils;

import com.leknos.netflixroll.TheMovieDbApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.leknos.netflixroll.utils.Constants.BASE_URL;

public class NetworkService {
    private static NetworkService mInstance;
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
