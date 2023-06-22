package com.mawumbo.movie.ui.navigation

sealed class MovieRoute(val route: String) {
    object Home : MovieRoute("home")
    object MovieDetail : MovieRoute("detail/{movieId}") {
        fun createRoute(movieId: String) = "detail/$movieId"
    }

    object About : MovieRoute("about")
}