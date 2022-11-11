package com.example.movieappalgo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.movieappalgo.apiservice.INITIAL_PAGE
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.model.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class _DataMovieSource (private val api_Service: Movie_Interface, private val disposable: CompositeDisposable) :
    PageKeyedDataSource<Int, Movie>() {

    private var page = INITIAL_PAGE

    val networkState: MutableLiveData<Network_State> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {

        networkState.postValue(Network_State.LOADING)

        disposable.add(
            api_Service.getPopular_Movie(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.movie_List, null, page+1)
                        networkState.postValue(Network_State.LOADED)
                    },
                    {
                        networkState.postValue(Network_State.ERROR)
                        it.message?.let { it1 -> Log.e("MovieDataSource", it1) }
                    }
                )
        )
    }

}