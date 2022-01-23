package com.example.trendingmovies.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String title;
    String synopsis;
    String imageUrl;
    String largeImageUrl;

    Movie(JSONObject jsonObject) throws JSONException {
        this.title = jsonObject.getString("title");
        this.synopsis = jsonObject.getString("overview");
        this.imageUrl = "https://image.tmdb.org/t/p/w500/" + jsonObject.getString("poster_path");
        this.largeImageUrl = "https://image.tmdb.org/t/p/w780/" +  jsonObject.getString("poster_path");
    }
    public Movie(String title, String synopsis, String imageUrl) {
        this.title = title;
        this.synopsis = synopsis;
        this.imageUrl = "https://image.tmdb.org/t/p/w500/" + imageUrl;
        this.largeImageUrl = "https://image.tmdb.org/t/p/w92/" + imageUrl;
    }

    //creates List of Movies from JSONArray
    public static List<Movie> fromJsonArray(JSONObject movieJsonObject) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < movieJsonObject.getJSONArray("results").length(); i++) {
            movies.add(
                new Movie(
                    movieJsonObject.getJSONArray("results").getJSONObject(i)
                )
            );
        }

        return movies;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLargeImageUrl() { return largeImageUrl; }
}
