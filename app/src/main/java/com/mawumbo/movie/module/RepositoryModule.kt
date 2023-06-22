package com.mawumbo.movie.module

import android.content.Context
import com.mawumbo.movie.data.repository.DefaultMovieRepository
import com.mawumbo.movie.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(@ApplicationContext context: Context): MovieRepository =
        DefaultMovieRepository(context)

}