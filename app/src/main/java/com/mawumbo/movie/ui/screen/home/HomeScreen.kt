package com.mawumbo.movie.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mawumbo.movie.R
import com.mawumbo.movie.data.model.Movie

@Composable
fun HomeScreen(
    navigateToDetail: (String) -> Unit,
    navigateToAbout: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.movies))
                },
                actions = {
                    var showMenu by remember { mutableStateOf(false) }

                    IconButton(onClick = {
                        showMenu = !showMenu
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = stringResource(id = R.string.menu_content_description),
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(onClick = { navigateToAbout() }) {
                            Text(stringResource(id = R.string.about_me))
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        val uiState by viewModel.movies.collectAsState()
        HomeContent(
            movies = uiState.movies,
            onMovieClick = navigateToDetail,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun HomeContent(
    movies: List<Movie>,
    onMovieClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(8.dp)
    ) {
        items(
            items = movies,
            key = { it.imdbID }
        ) { movie ->
            MovieItem(
                movie = movie,
                onClick = onMovieClick
            )
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick(movie.imdbID) }
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            AsyncImage(
                model = movie.poster,
                contentDescription = stringResource(id = R.string.movie_poster_content_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.year,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                )
                Text(
                    text = movie.plot,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {

}