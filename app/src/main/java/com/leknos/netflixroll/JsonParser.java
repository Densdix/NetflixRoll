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
import java.util.Iterator;

public class JsonParser {
    JSONObject parser;
    private int id;
    private String title;
    private String textData;
    private String posterPath;
    private double voteAverage;
    private String overview;



    public Movie jsonParser(String response) {
        String result = "";
        try {

            parser = new JSONObject(response);
            JSONArray results = parser.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                id = results.getJSONObject(i).getInt("id");
                title = results.getJSONObject(i).getString("title");
                result+="id: "+String.valueOf(id)+"\n"+"title: "+ title+"\n\n";
            }

                //textData = results.getJSONObject(0).getString("release_date");
                //posterPath = results.getJSONObject(0).getString("poster_path");
                //voteAverage = results.getJSONObject(0).getDouble("vote_average");
                //overview = results.getJSONObject(0).getString("overview");

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return new Movie(id, result, "none", "none", 0, "none");
        }

    }


}
