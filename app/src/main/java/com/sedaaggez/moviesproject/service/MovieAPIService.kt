package com.sedaaggez.moviesproject.service

import com.sedaaggez.moviesproject.model.MoviesData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getMoviesData(
        apiKey: String,
        page: Int
    ): Single<MoviesData> {
        return api.getMoviesData(apiKey, page)
    }
}