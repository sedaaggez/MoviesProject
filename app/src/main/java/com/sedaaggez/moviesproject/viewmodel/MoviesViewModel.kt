package com.sedaaggez.moviesproject.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sedaaggez.moviesproject.model.MoviesData
import com.sedaaggez.moviesproject.model.MoviesResult
import com.sedaaggez.moviesproject.service.MovieAPIService
import com.sedaaggez.moviesproject.service.MovieDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application) : BaseViewModel(application) {

    private val movieApiService = MovieAPIService()
    private val disposable = CompositeDisposable()

    val results = MutableLiveData<List<MoviesResult>>()
    val totalPage = MutableLiveData<Int>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getData(apiKey: String, page: Int) {
        loading.value = true

        disposable.add(
            movieApiService.getMoviesData(apiKey, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MoviesData>() {
                    override fun onSuccess(t: MoviesData) {
                        t.results?.let { storeInSQLite(it) }
                        totalPage.value = t.total_pages
                    }

                    override fun onError(e: Throwable) {
                        error.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }


    private fun storeInSQLite(list: List<MoviesResult>) {
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
            dao.deleteAllResults()
            val listLong = dao.insertAll(*list.toTypedArray())
            showMovies(list)
        }
    }

    private fun showMovies(movieList: List<MoviesResult>) {
        results.value = movieList
        error.value = false
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}