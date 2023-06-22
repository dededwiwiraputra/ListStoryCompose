package com.mawumbo.movie.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawumbo.movie.data.model.Movie
import com.mawumbo.movie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUiState(
    val movies: List<Movie> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {

    val movies: StateFlow<HomeUiState> = repository.getMovies()
        .map { movies ->
            HomeUiState(movies)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HomeUiState()
        )

}
