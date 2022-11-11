package com.example.movieappalgo.frontui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer


import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappalgo.R
import com.example.movieappalgo.apiservice.Movie_Client
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.frontui.popular.MainActivity_ViewModel
import com.example.movieappalgo.frontui.popular.MoviePagedListRepository
import com.example.movieappalgo.frontui.popular.PopularMoviePagedListAdapter
import com.example.movieappalgo.repository.Network_State
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivity_ViewModel
    private lateinit var progress_bar_popular:ProgressBar

    lateinit var movieRepository: MoviePagedListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rv_movie_list : RecyclerView = findViewById(R.id.rv_movie_list)
        progress_bar_popular = findViewById(R.id.progress_bar)

        val apiService : Movie_Interface = Movie_Client.getClient()
        movieRepository = MoviePagedListRepository(apiService)

        viewModel = getViewModel()

        val movieAdapter = PopularMoviePagedListAdapter(this)

        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return  1
                else return 3
            }
        };

        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

       viewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility = if (viewModel.listIsEmpty() && it == Network_State.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility = if (viewModel.listIsEmpty() && it == Network_State.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })



    }


    private fun getViewModel(): MainActivity_ViewModel {

        return MainActivity_ViewModel(movieRepository)

    }
    }



