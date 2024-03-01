package com.example.pexelapp.data.repository

import android.util.Log
import com.example.pexelapp.data.api.ApiService
import com.example.pexelapp.data.models.asDomain
import com.example.pexelapp.domain.model.CollectionDomain
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.repository.PhotoRepository
import com.example.pexelapp.utils.Constants.API_KEY

class PhotoRepositoryImpl(private val apiService: ApiService) : PhotoRepository {
    override suspend fun getFeaturedCollectionList(
        page: Int,
        per_page: Int

    ): List<CollectionDomain> {
        val list = apiService
                .getFeaturedCollections(API_KEY, page, per_page)
                .collections
                .asDomain()
        return list
    }

    override suspend fun getPhotosList(
        query: String,
        per_page: Int,
        page: Int
    ): List<PhotoDomain> {
        val list = apiService
                .getPhotos(API_KEY, query, per_page, page)
                .photos
        return list
    }


    override suspend fun getCuratedPhotosList(
        per_page: Int,
        page: Int
    ): List<PhotoDomain> {
        val list = apiService
                .getCuratedPhotos(API_KEY, per_page, page)
                .photos
        return list
    }

    override suspend fun getPhotoById(id: Int): PhotoDomain? {
        val photo = apiService
                .getPhotoById(API_KEY, id)
                .asDomain()
        return photo
    }
}