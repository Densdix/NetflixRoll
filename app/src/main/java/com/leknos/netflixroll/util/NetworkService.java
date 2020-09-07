package com.leknos.netflixroll.util;

import com.leknos.netflixroll.api.TheMovieDbApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.leknos.netflixroll.util.Constants.BASE_URL;

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
