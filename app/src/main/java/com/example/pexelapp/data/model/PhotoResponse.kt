package com.example.pexelapp.data.model

import com.example.pexelapp.domain.model.PhotoDomain

data class PhotoResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<PhotoDomain>,
    val total_results: Int
)
