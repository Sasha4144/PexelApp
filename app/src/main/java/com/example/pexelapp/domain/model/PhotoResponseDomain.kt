package com.example.pexelapp.domain.model

data class PhotoResponseDomain(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<PhotoDomain>,
    val total_results: Int
)