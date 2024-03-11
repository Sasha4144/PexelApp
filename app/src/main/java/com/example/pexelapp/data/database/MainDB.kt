package com.example.pexelapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pexelapp.data.model.Photo
import com.example.pexelapp.presentation.di.Dao

@Database(
    entities = [Photo::class],
    version = 1
)
abstract class MainDB : RoomDatabase() {
    abstract val dao: Dao
}