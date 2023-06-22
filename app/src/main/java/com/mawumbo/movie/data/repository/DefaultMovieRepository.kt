package com.mawumbo.movie.data.repository

import android.content.Context
import com.mawumbo.movie.data.helper.parseJsonToList
import com.mawumbo.movie.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultMovieRepository(
    context: Context
) : MovieRepository {

    private val movies: Map<String, Movie> = context.parseJsonToList<Movie>("json/data.json")
        .associateBy({ it.imdbID }, { it })

    override fun getMovies(): Flow<List<Movie>> = flow {
        emit(movies.values.toList())
    }

    override fun getMovie(id: String): Flow<Movie> = flow {
        emit(movies[id] ?: throw NoSuchElementException("No such movie"))
    }

}