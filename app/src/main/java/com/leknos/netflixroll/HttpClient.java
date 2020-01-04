package com.leknos.netflixroll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpClient {
    public static final String xmlRequest = "https://api.themoviedb.org/3/search/movie?api_key=862c044119db5df703d9b0d454af8251&language=en-US&query=";
    public static final String posterRequest = "https://image.tmdb.org/t/p/w220_and_h330_face";
    private JsonParser jsonParser;
    private ArrayList<Movie> movies;

    public HttpClient() {
        jsonParser = new JsonParser();
    }

    public ArrayList<Movie> readMovieInfo(String searchWord) {
        try {
            String fullRequest = xmlRequest + searchWord;
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
