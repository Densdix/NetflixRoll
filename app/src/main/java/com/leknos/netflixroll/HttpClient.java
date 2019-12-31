package com.leknos.netflixroll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {
    public static final String xmlRequest = "https://api.themoviedb.org/3/search/movie?api_key=862c044119db5df703d9b0d454af8251&language=en-US&query=";
    private JsonParser jsonParser;

    public HttpClient() {
        jsonParser = new JsonParser();
    }

    public Movie readMovieInfo(String searchWord) {
        Movie movie = null;
        try {
            String fullRequest = xmlRequest + searchWord;
            URL url = new URL(fullRequest);
            String response = convertStreamToString(url.openStream());
            movie = jsonParser.jsonParser(response);
        }catch (Exception e){
            e.fillInStackTrace();
        }finally {
            return movie;
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
