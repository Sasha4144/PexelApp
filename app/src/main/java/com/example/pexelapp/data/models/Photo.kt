package com.example.pexelapp.data.models

import com.example.pexelapp.domain.model.PhotoDomain
import com.example.pexelapp.domain.model.SrcDomain
import java.io.Serializable

data class Photo (
    val id: Int,
    val alt: String,
    val avg_color: String,
    val height: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: Src,
    val url: String,
    val width: Int
) : Serializable

fun Photo.asDomain(): PhotoDomain {
    return PhotoDomain(
        id = this.id,
        width = this.width,
        height = this.height,
        url = this.url,
        photographer = this.photographer,
        photographer_url = this.photographer_url,
        photographer_id = this.photographer_id,
        avg_color = this.avg_color,
        src = SrcDomain(
            original = this.src.original,
            large2x = this.src.large2x,
            large = this.src.large,
            medium = this.src.medium,
            small = this.src.small,
            portrait = this.src.portrait,
            landscape = this.src.landscape,
            tiny = this.src.tiny,
        ),
        liked = this.liked,
        alt = this.alt
    )
}

fun PhotoDomain.asPhoto(): Photo {
    return Photo(
        id = this.id,
        width = this.width,
        height = this.height,
        url = this.url,
        photographer = this.photographer,
        photographer_url = this.photographer_url,
        photographer_id = this.photographer_id,
        avg_color = this.avg_color,
        src = Src(
            original = this.src.original,
            large2x = this.src.original,
            large = this.src.original,
            medium = this.src.original,
            small = this.src.original,
            portrait = this.src.original,
            landscape = this.src.original,
            tiny = this.src.original,
        ),
        liked = this.liked,
        alt = this.alt,
    )
}