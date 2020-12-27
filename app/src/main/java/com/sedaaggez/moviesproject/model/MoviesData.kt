package com.sedaaggez.moviesproject.model

import com.google.gson.annotations.SerializedName

data class MoviesData(
    val page: Int?,
    @SerializedName("total_pages")
    val total_pages: Int?,
    @SerializedName("total_results")
    val total_results: Int?,
    val results: List<MoviesResult>?
)
