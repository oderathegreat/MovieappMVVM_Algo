package com.example.movieappalgo.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.model.Movie
import io.reactivex.disposables.CompositeDisposable

class DataFactory (private val api : Movie_Interface, private val compdisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie >() {

    val _moviesliveData =  MutableLiveData<_DataMovieSource>()



    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = _DataMovieSource(api,compdisposable)

       _moviesliveData.postValue(movieDataSource)
        return movieDataSource
    }

    }