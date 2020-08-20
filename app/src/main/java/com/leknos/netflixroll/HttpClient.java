package com.leknos.netflixroll;

import com.leknos.netflixroll.model.Movie;
import com.leknos.netflixroll.utils.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.leknos.netflixroll.utils.Constants.API_KEY_VALUE;
import static com.leknos.netflixroll.utils.Constants.BASE_URL;

public class HttpClient {
    public static final String MOVIE_REQUEST = BASE_URL+"search/movie?api_key="+API_KEY_VALUE+"&language=en-US&query=";

    private JsonParser jsonParser;
    private ArrayList<Movie> movies;

    public HttpClient() {
        jsonParser = new JsonParser();
    }

    public ArrayList<Movie> readMovieInfo(String searchWord) {
        try {
            String fullRequest = MOVIE_REQUEST + searchWord;
            URL url = new URL(fullRequest);
            String response = convertStreamToString(url.openStream());
            movies = jsonParser.jsonParser(response);
        }catch (Exception e){
            e.fillInStackTrace();
        }finally {
            return movies;
        }

    }

    public String convertStreamToString(InputStream in){

        String line;
        StringBuilder sb = new StringBuilder();
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
