package com.example.movieappalgo.frontui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieappalgo.model.Movie_Detail
import com.example.movieappalgo.repository.Network_State
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository :MovieDetailesRepository,movieId:Int): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val movieDetails : LiveData<Movie_Detail> by lazy{
        movieRepository.fetchSingleMovieDetails(compositeDisposable,movieId)
    }

    val networkState : LiveData<Network_State> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}