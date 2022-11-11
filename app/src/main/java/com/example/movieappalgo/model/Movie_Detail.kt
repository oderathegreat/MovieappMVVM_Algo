package com.example.movieappalgo.model

import android.media.Rating
import com.google.gson.annotations.SerializedName

data class Movie_Detail(


    val overview:String,
    val revenue: String,
    val popularity: String,
    val tagline: String,
    val title:String,
    val rating: Double,
    val video : Boolean,
    @SerializedName("poster_path")
    val poster_Path:String,
    val id:Int,
    @SerializedName("release_date")
    val releaseDate:String




)
