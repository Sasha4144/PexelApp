package com.example.pexelapp.domain.repository

import com.example.pexelapp.domain.model.CollectionDomain
import com.example.pexelapp.domain.model.PhotoDomain

interface WebRepository {
    suspend fun getFeaturedCollectionList(
        page: Int,
        per_page: Int
    ): List<CollectionDomain>

    suspend fun getPhotosList(
        query: String,
        per_page: Int,
        page: Int
    ): List<PhotoDomain>

    suspend fun getCuratedPhotosList(
        per_page: Int,
        page: Int
    ): List<PhotoDomain>

    suspend fun getPhotoById(
        id: Int
    ): PhotoDomain?
}