package com.example.movieappalgo.frontui

import androidx.lifecycle.LiveData
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.model.Movie_Detail
import com.example.movieappalgo.repository.Network_State
import com.example.movieappalgo.repository._DataMovieDetailsSource
import io.reactivex.disposables.CompositeDisposable

class MovieDetailesRepository (private val apiService: Movie_Interface) {

    lateinit var movieDetailsNetworkDataSource: _DataMovieDetailsSource
    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<Movie_Detail> {

        movieDetailsNetworkDataSource =
            _DataMovieDetailsSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<Network_State> {
        return movieDetailsNetworkDataSource.networkState
    }


}