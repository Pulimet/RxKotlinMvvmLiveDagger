package net.alexandroid.rxkotlinmvvmlivedagger.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(val id: String, val likes: Int, val favorites: Int,
                 val tags: String, val previewURL: String, val webformatURL: String) : Parcelable