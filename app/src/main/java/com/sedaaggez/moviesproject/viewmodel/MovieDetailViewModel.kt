package com.sedaaggez.moviesproject.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.moviesproject.model.MoviesResult
import com.sedaaggez.moviesproject.service.MovieDatabase
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {

    val movieResultLiveData = MutableLiveData<MoviesResult>()

    fun getDataFromRoom(movieId: Int) {

        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
            val movieResult = dao.getResult(movieId)
            movieResultLiveData.value = movieResult
        }
    }
}