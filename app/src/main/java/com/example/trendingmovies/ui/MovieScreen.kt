package com.example.trendingmovies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.trendingmovies.models.Movie
import com.example.trendingmovies.ui.theme.DeepGray
import com.example.trendingmovies.ui.theme.GreyBackground
import com.example.trendingmovies.ui.theme.TextGrey
import com.example.trendingmovies.ui.theme.Yellow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MovieScreen(
    navigator: DestinationsNavigator,
    movieTitle: String,
    movieSynopsis: String,
    movieImageUrl: String
) {
    val movie = Movie(movieTitle, movieSynopsis, movieImageUrl)

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = "Get Reservation")
                },
                onClick = {

                },
                backgroundColor = Yellow,
                contentColor = Color.Black
            )
        },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(GreyBackground)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier
                            .padding(bottom = 20.dp),
                        painter = rememberImagePainter(
                            data = movie.imageUrl,
                            builder = {
                                size(OriginalSize)
                            }
                        ),
                        contentDescription = null,
                    )
                    Text(

                        text = movie.title,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Divider(
                        color = DeepGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp)
                            .width(2.dp)
                    )
                    SynopsisSection(synopsis = movie.synopsis)
                }
            }
        }
    )
}

@Composable
fun SynopsisSection(synopsis: String) {
    Text(
        modifier = Modifier.padding(bottom = 15.dp),
        text = "Synopsis",
        style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    )
    Text(
        text = synopsis,
        color = TextGrey,
        style = MaterialTheme.typography.caption.copy(
            lineHeight = 25.sp
        )
    )
}