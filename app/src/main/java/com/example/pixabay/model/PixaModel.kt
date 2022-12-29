package com.example.pixabay.model

data class PixaModel(
    var hits: List<ImageModel>? = null
)

data class ImageModel(
    var largeImageURL: String? = null
)


