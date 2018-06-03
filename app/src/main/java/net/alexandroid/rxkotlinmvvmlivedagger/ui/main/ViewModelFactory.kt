package net.alexandroid.rxkotlinmvvmlivedagger.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import net.alexandroid.rxkotlinmvvmlivedagger.api.PhotoRetriever

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val photoRetriever: PhotoRetriever) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(photoRetriever) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}