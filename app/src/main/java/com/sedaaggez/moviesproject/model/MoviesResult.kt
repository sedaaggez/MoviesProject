package com.sedaaggez.moviesproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MoviesResult(
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,
    @ColumnInfo(name = "origin_country")
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String?,
    @ColumnInfo(name = "original_name")
    @SerializedName("original_name")
    val originalName: String?,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double?,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int?
) {
    val imageUrl: String
        get() = String.format("https://image.tmdb.org/t/p/original/$posterPath")

    val formattedCountry: String
        get() = String.format("Country: " + originCountry)

    val formattedFirstAirDate: String
        get() = String.format("First Air Date: " + firstAirDate)

    val formattedVoteAverage: String
        get() = String.format("Vote Average: " + voteAverage)

    val formattedVoteCount: String
        get() = String.format("Vote Count: " + voteCount)

    val formattedVotePopularity: String
        get() = String.format("Popularity: " + popularity)


}
