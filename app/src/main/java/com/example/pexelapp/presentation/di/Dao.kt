package com.example.pexelapp.presentation.di

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pexelapp.data.model.Photo

@Dao
interface Dao {

    @Query("SELECT * FROM photos WHERE id LIKE :id")
    suspend fun getPhotoById(id: Int): List<Photo>

    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<Photo>

    @Insert()
    suspend fun addPhoto(photo: Photo)

    @Delete
    suspend fun removePhoto(photo: Photo)

    @Query("SELECT COUNT(*) FROM photos WHERE id = :id")
    suspend fun countById(id: Int): Int
}