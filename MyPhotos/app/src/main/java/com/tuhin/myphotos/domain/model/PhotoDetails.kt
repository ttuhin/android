package com.tuhin.myphotos.domain.model

data class PhotoDetails(
    val photoId: Int,
    val photoTitle: String,
    val albumID: Int,
    val albumName: String,
    val userName: String,
    val thumbnailUrl: String
)
