package com.example.pexelapp.domain.repository

import com.example.pexelapp.data.model.Photo
import com.example.pexelapp.domain.model.PhotoDomain

interface DatabaseRepository {
    suspend fun getPhotoById(id: Int): PhotoDomain

    suspend fun getPhotos(): List<PhotoDomain>

    suspend fun addPhoto(photo: Photo)

    suspend fun removePhoto(photo: Photo)

    suspend fun countPhotosById(id: Int): Int
}