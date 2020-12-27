package com.sedaaggez.moviesproject.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedaaggez.moviesproject.model.MoviesResult
import com.sedaaggez.moviesproject.util.Converters

@Database(entities = arrayOf(MoviesResult::class), version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile private var instance : MovieDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, MovieDatabase::class.java, "moviedatabase"
        ).build()
    }
}