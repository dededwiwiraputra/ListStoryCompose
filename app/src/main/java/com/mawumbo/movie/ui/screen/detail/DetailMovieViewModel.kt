package com.mawumbo.movie.ui.screen.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawumbo.movie.data.model.Movie
import com.mawumbo.movie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class DetailMovieUiState(
    val movie: Movie = Movie()
)

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: MovieRepository
) : ViewModel() {

    private val movieId: String = savedStateHandle["movieId"] ?: "-1"

    val uiState: StateFlow<DetailMovieUiState> = repository.getMovie(movieId)
        .catch { throwable ->
            Log.i("DetailMovie", throwable.message ?: "Unexpected error")
        }.map { movie ->
            DetailMovieUiState(
                movie = movie
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = DetailMovieUiState()
        )

}