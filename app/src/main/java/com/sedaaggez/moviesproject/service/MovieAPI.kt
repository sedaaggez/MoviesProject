package com.sedaaggez.moviesproject.service

import com.sedaaggez.moviesproject.model.MoviesData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    // https://api.themoviedb.org/3/
    // tv/popular?apikey=""&page=

    @GET("tv/popular")
    fun getMoviesData(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<MoviesData>
}