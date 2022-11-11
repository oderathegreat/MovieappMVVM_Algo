package com.example.movieappalgo.apiservice

import com.example.movieappalgo.model._Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Movie_Interface {


    @GET("movie/popular")
    fun getPopular_Movie(@Query("page")page:Int) : Single<_Response>
    @GET("movie/{movie_id}")
    fun getMovie_Detail(@Path("movie_id")id:Int) : Single<_Response>
}