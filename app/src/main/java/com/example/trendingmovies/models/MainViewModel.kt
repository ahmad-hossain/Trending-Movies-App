package com.example.trendingmovies.models

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.coroutines.launch
import okhttp3.Headers

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"

    val movies = mutableStateListOf<Movie>()

    init {
        getTrendingMoviesJson()
    }

    private fun getTrendingMoviesJson() {
        val apiKey = "REDACTED"
        val url = "https://api.themoviedb.org/3/trending/movie/day"
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = apiKey

        viewModelScope.launch {
            client[url, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                    movies.clear()
                    movies.addAll(Movie.fromJsonArray(json?.jsonObject))

                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    Log.d(TAG, "Request Failure")
                }
            }]
        }
    }
}