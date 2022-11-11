package com.example.movieappalgo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.model.Movie_Detail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class _DataMovieDetailsSource(private val api: Movie_Interface, private val composedispose: CompositeDisposable) {

    private val network_State = MutableLiveData<Network_State>()

    val networkState: LiveData<Network_State>
        get() = network_State

    private val _downloadedMovieDetailsResponse = MutableLiveData<Movie_Detail>()
    val downloadedMovieResponse: LiveData<Movie_Detail>
        get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {
        network_State.postValue(Network_State.LOADING)
        try {
            composedispose.add(
                api.getMovie_Detail(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse
                            network_State.postValue(Network_State.LOADED)
                        },
                        {
                            network_State.postValue(Network_State.ERROR)
                            it.message?.let { it1 -> Log.e("MovieDetailsDataSource", it1) }
                        }
                    )
            )

        }

        catch (e: Exception){
            e.message?.let { Log.e("MovieDetailsDataSource", it) }
        }

    }


}