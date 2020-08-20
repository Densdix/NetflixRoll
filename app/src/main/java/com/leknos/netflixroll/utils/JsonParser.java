package com.leknos.netflixroll.utils;

import com.leknos.netflixroll.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
