package com.example.movieappalgo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieappalgo.apiservice.Movie_Interface
import io.reactivex.disposables.CompositeDisposable

class _DataMovieDetailsSource(private val api: Movie_Interface, private val composedispose: CompositeDisposable) {

    private val network_State = MutableLiveData<Network_State>()

    val networkState: LiveData<Network_State>
        get() = network_State


}