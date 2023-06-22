package com.mawumbo.movie.data.repository

import com.mawumbo.movie.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<List<Movie>>

    fun getMovie(id: String): Flow<Movie>

}