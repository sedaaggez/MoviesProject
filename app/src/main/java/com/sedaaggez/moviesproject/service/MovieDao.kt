package com.sedaaggez.moviesproject.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sedaaggez.moviesproject.model.MoviesResult

@Dao
interface MovieDao {
    @Insert
    suspend fun insertAll(vararg results: MoviesResult): List<Long>

    @Query("SELECT * FROM moviesResult")
    suspend fun getAllResults(): List<MoviesResult>

    @Query("SELECT * FROM moviesResult WHERE id = :moviesId")
    suspend fun getResult(moviesId: Int): MoviesResult

    @Query("DELETE FROM moviesResult")
    suspend fun deleteAllResults()
}