package com.example.movieappalgo.model

import com.google.gson.annotations.SerializedName

data class Movie(
   val title: String,
   @SerializedName("release_date")
   val releaseDate: String,
   @SerializedName("poster_path")
   val poster_Path:String
)
