package net.alexandroid.rxkotlinmvvmlivedagger.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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