package net.alexandroid.rxkotlinmvvmlivedagger.model

data class Photo(val id: String, val likes: Int, val favorites: Int,
                 val tags: String, val previewURL: String, val webformatURL: String)