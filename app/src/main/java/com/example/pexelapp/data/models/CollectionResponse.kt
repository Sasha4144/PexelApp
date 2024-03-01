package com.example.pexelapp.data.models

import com.example.pexelapp.domain.model.CollectionDomain

data class CollectionsResponse(
    val collections: List<Collection>,
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val prev_page: String,
    val total_results: Int
)

fun List<Collection>.asDomain(): List<CollectionDomain> {
    return map {
        CollectionDomain(
            id = it.id,
            title = it.title,
            description = it.description,
            private = it.private,
            media_count = it.media_count,
            photos_count = it.photos_count,
            videos_count = it.videos_count
        )
    }
}
