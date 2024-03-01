package com.example.pexelapp.domain.model

data class CollectionResponseDomain(
    val collections: List<CollectionDomain>,
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val prev_page: String,
    val total_results: Int
)