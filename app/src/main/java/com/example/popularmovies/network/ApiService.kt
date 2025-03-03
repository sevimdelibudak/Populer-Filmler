package com.example.popularmovies.network

import android.media.session.MediaSession.Token
import com.example.popularmovies.model.MovieDetailResponse
import com.example.popularmovies.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("popular?language=tr")
    suspend fun getMovieList(@Header("Authorization")token: String): Response<MovieResponse>

    @GET("{movieId}?language=tr")
    suspend fun getMovieDetail(@Path("movieId") movieId: String, @Header("Authorization")token: String ) : Response<MovieDetailResponse>
}
