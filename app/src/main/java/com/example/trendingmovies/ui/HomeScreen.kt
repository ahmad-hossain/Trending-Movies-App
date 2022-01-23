package com.example.trendingmovies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.trendingmovies.R
import com.example.trendingmovies.models.MainViewModel
import com.example.trendingmovies.models.Movie
import com.example.trendingmovies.ui.destinations.MovieScreenDestination
import com.example.trendingmovies.ui.theme.DeepGray
import com.example.trendingmovies.ui.theme.TextGrey
import com.example.trendingmovies.ui.theme.TextWhite
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    var searchState by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            WelcomeSection()
            SearchSection(searchState) {
                searchState = it
            }
            TrendingSection() { movie ->
                navigator.navigate(
                    MovieScreenDestination(
                        movie.title,
                        movie.synopsis,
                        movie.imageUrl
                    )
                )
            }
        }
    }
}

@Composable
fun WelcomeSection(
    name: String = "Angeline"
) {
    Column(
        modifier = Modifier
            .padding(top = 30.dp, start = 20.dp, bottom = 20.dp)
    ) {
        Text(
            text = "Welcome $name",
            style = MaterialTheme.typography.caption,
            color = TextWhite
        )
        Text(
            text = "Let's relax and watch a movie!",
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.White
        )
    }
}

@Composable
fun SearchSection(
    searchState: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = DeepGray,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(CircleShape),
        value = searchState,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(15.dp),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = TextGrey
            )
        },
        label = {
            Text(
                text = "Search",
                color = TextGrey
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun TrendingSection(
    viewModel: MainViewModel = viewModel(),
    onMovieClick: (Movie) -> Unit
) {
    LazyRow {
        items(viewModel.movies.size) {
            MovieCard(viewModel.movies[it]) {
                onMovieClick(viewModel.movies[it])
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MovieCard(
    movie: Movie,
    onMovieClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = 10.dp,
        onClick = {
            onMovieClick()
        }
    ) {
        Image(
            painter = rememberImagePainter(
                data = movie.imageUrl,
                builder = {
                    // don't use it blindly, it can be tricky.
                    // check out https://stackoverflow.com/a/68908392/3585796
                    size(OriginalSize)
                }
            ),
            contentDescription = null,
        )
    }
}