package com.example.movieappalgo.frontui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movieappalgo.R
import com.example.movieappalgo.apiservice.Movie_Client
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.apiservice.POSTER_BASE_URL
import com.example.movieappalgo.frontui.popular.MainActivity_ViewModel
import com.example.movieappalgo.model.Movie_Detail
import com.example.movieappalgo.repository.Network_State
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_single.*

import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)



        val movieId:Int=intent.getIntExtra("id",1)

        val apiService:Movie_Interface = Movie_Client.getClient()
        movieRepository= MovieDetailesRepository(apiService)

        viewModel= getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility=if(it== Network_State.LOADING) View.VISIBLE else View.GONE
            //txt_error.visibility=if(it== Network_State.ERROR) View.VISIBLE else View.GONE
        })
    }

    fun bindUI( it: Movie_Detail){
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        //movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)

        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.poster_Path
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);


    }
    private fun getViewModel(movieId: Int): SingleMovieViewModel {

        return SingleMovieViewModel(movieRepository,movieId)

    }

}