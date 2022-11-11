package com.example.movieappalgo.model

import com.google.gson.annotations.SerializedName

data class _Response(

 @SerializedName("total_pages")
val _totalPageCount:Int,
 @SerializedName("total_results")
val _totalResults:Int,
 @SerializedName("results")
val movie_List: List<Movie>,
@SerializedName("page")
val _page:Int

)
