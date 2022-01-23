package com.example.trendingmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import com.example.trendingmovies.ui.HomeScreen
import com.example.trendingmovies.ui.NavGraphs
import com.example.trendingmovies.ui.theme.GreyBackground
import com.example.trendingmovies.ui.theme.TrendingMoviesTheme
import com.ramcosta.composedestinations.DestinationsNavHost

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrendingMoviesTheme {
                Surface(color = GreyBackground) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}