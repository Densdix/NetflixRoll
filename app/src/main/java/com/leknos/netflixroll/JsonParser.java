package com.leknos.netflixroll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser {
    JSONObject parser;
    private int id;
    private String title;
    private String textData;
    private String posterPath;
    private double voteAverage;
    private String overview;
    private ArrayList<Movie> movies;



    public ArrayList<Movie> jsonParser(String response) {
        movies = new ArrayList<>();

        try {
            parser = new JSONObject(response);
            JSONArray results = parser.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                id = results.getJSONObject(i).getInt("id");
                title = results.getJSONObject(i).getString("title");
                textData = results.getJSONObject(i).getString("release_date");
                posterPath = results.getJSONObject(i).getString("poster_path");
                voteAverage = results.getJSONObject(i).getDouble("vote_average");
                overview = results.getJSONObject(i).getString("overview");
                movies.add(new Movie(id, title, textData, posterPath, voteAverage, overview));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return movies;
        }

    }


}
