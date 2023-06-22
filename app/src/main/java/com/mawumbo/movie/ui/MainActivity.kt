package com.mawumbo.movie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mawumbo.movie.data.model.Movie
import com.mawumbo.movie.ui.navigation.MovieRoute
import com.mawumbo.movie.ui.screen.about.AboutScreen
import com.mawumbo.movie.ui.screen.detail.DetailMovieScreen
import com.mawumbo.movie.ui.screen.home.HomeScreen
import com.mawumbo.movie.ui.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeTheme {
                MovieApp()
            }
        }
    }
}

@Composable
fun MovieApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MovieRoute.Home.route
    ) {
        composable(MovieRoute.Home.route) {
            HomeScreen(
                navigateToDetail = { id ->
                    navController.navigate(MovieRoute.MovieDetail.createRoute(id))
                },
                navigateToAbout = {
                    navController.navigate(MovieRoute.About.route)
                }
            )
        }
        composable(
            route = MovieRoute.MovieDetail.route,
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) {
            DetailMovieScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(MovieRoute.About.route) {
            AboutScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    MovieApp()
}