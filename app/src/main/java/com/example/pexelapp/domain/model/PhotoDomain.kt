package com.example.pexelapp.domain.model

import java.io.Serializable

data class PhotoDomain(
    val alt: String,
    val avg_color: String,
    val height: Int,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: SrcDomain,
    val url: String,
    val width: Int
) : Serializable