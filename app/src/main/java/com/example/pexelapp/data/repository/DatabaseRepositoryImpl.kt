package com.example.pexelapp.data.repository

import com.example.pexelapp.data.database.MainDB
import com.example.pexelapp.data.model.Photo
import com.example.pexelapp.data.model.asDomain
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(private val db: MainDB) : DatabaseRepository {
    override suspend fun getPhotoById(id: Int): PhotoDomain {
        val photo = db.dao.getPhotoById(id).first()
        return photo.asDomain()
    }

    override suspend fun getPhotos(): List<PhotoDomain> {
        val photos = db.dao.getPhotos()
        return photos.map { it.asDomain() }
    }

    override suspend fun addPhoto(photo: Photo) {
        db.dao.addPhoto(photo)
    }

    override suspend fun removePhoto(photo: Photo) {
        db.dao.removePhoto(photo)
    }

    override suspend fun countPhotosById(id: Int): Int {
        val count = db.dao.countById(id)
        return count
    }

}