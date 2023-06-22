package com.mawumbo.movie.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mawumbo.movie.data.model.Movie
import com.mawumbo.movie.ui.theme.BiloxiBLue
import com.mawumbo.movie.R

@Composable
fun DetailMovieScreen(
    navigateBack: () -> Unit
) {
    val viewModel: DetailMovieViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_content_description)
                        )
                    }
                },
                title = {
                    Text(text = "Detail Movie")
                }
            )
        }
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsState()
        DetailMovieContent(
            movie = uiState.movie,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun DetailMovieContent(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Start
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(text = movie.year)
                Text(text = movie.rated)
                Text(text = movie.runtime)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = movie.poster,
                    contentScale = ContentScale.Inside,
                    contentDescription = stringResource(id = R.string.movie_image_content_description),
                    modifier = Modifier
                        .height(200.dp)
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = movie.genre,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(
                        color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(6.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Start
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(text = stringResource(
                    id = R.string.label_imdb_rating,
                    movie.imdbRating
                ), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                Text(text = stringResource(
                    id = R.string.label_imdb_vote,
                    movie.imdbVotes
                ), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Start
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(text = stringResource(
                    id = R.string.label_director,
                    movie.director
                ), style = TextStyle(fontSize = 16.sp))
                Text(text = "||", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                Text(text = stringResource(
                    id = R.string.label_writer,
                    movie.writer
                ), style = TextStyle(fontSize = 16.sp))
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = stringResource(
                    id = R.string.label_actors,
                    movie.actors
                ),
                modifier = modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = stringResource(
                    id = R.string.label_synopsis,
                    movie.plot
                ),
                modifier = modifier.padding(start = 8.dp)
            )
        }
    }
}