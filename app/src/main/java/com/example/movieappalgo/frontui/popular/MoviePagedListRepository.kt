package com.example.movieappalgo.frontui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.apiservice._PER_PAGE_POST
import com.example.movieappalgo.model.Movie
import com.example.movieappalgo.repository.DataFactory
import com.example.movieappalgo.repository.Network_State
import com.example.movieappalgo.repository._DataMovieSource
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository (private val apiService : Movie_Interface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: DataFactory
    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = DataFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(_PER_PAGE_POST)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<Network_State> {
        return Transformations.switchMap<_DataMovieSource, Network_State>(
            moviesDataSourceFactory._moviesliveData, _DataMovieSource::networkState)
    }
}