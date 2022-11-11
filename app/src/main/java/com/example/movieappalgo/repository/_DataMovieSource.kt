package com.example.movieappalgo.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.movieappalgo.apiservice.INITIAL_PAGE
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.model.Movie
import io.reactivex.disposables.CompositeDisposable

class _DataMovieSource (private val api_Service: Movie_Interface, private val disposable: CompositeDisposable) :
    PageKeyedDataSource<Int, Movie>() {

    private var page = INITIAL_PAGE

    val networkState: MutableLiveData<Network_State> = MutableLiveData()

}