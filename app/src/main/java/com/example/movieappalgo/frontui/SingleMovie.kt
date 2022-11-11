package com.example.movieappalgo.frontui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.movieappalgo.R
import com.example.movieappalgo.apiservice.Movie_Client
import com.example.movieappalgo.apiservice.Movie_Interface
import com.example.movieappalgo.frontui.popular.MainActivity_ViewModel

class SingleMovie : AppCompatActivity() {


    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        val movieId:Int=intent.getIntExtra("id",1)

        val apiService:Movie_Interface= Movie_Client.getClient()
        movieRepository= MovieDetailesRepository(apiService)

        viewModel=getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })


    }


}   private fun getViewModel(): MainActivity_ViewModel {

    return MainActivity_ViewModel(movieRepository)

}