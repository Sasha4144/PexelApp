package com.example.pexelapp.data.repository

import com.example.pexelapp.data.api.ApiService
import com.example.pexelapp.data.model.asDomain
import com.example.pexelapp.domain.model.CollectionDomain
import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.repository.WebRepository
import com.example.pexelapp.utils.Constants

class WebRepositoryImpl(private val apiService: ApiService) : WebRepository {
    override suspend fun getFeaturedCollectionList(
        page: Int,
        per_page: Int

    ): List<CollectionDomain> {
        val list = apiService
            .getFeaturedCollections(Constants.API_KEY, page, per_page)
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
            .getPhotos(Constants.API_KEY, query, per_page, page)
            .photos
        return list
    }

    override suspend fun getCuratedPhotosList(
        per_page: Int,
        page: Int
    ): List<PhotoDomain> {
        val list = apiService
            .getCuratedPhotos(Constants.API_KEY, per_page, page)
            .photos
        return list
    }

    override suspend fun getPhotoById(id: Int): PhotoDomain? {
        val photo = apiService
            .getPhotoById(Constants.API_KEY, id)
            .asDomain()
        return photo
    }
}