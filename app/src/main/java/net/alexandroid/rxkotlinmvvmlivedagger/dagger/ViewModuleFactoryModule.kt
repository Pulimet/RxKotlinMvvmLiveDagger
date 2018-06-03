package net.alexandroid.rxkotlinmvvmlivedagger.dagger

import dagger.Module
import dagger.Provides
import net.alexandroid.rxkotlinmvvmlivedagger.api.PhotoRetriever
import net.alexandroid.rxkotlinmvvmlivedagger.ui.main.ViewModelFactory

@Module(includes = [(PhotosApiModule::class)])
class ViewModuleFactoryModule {
    @MyApplicationScope
    @Provides
    fun provideViewModuleFactory(photoRetriever: PhotoRetriever): ViewModelFactory {
        return ViewModelFactory(photoRetriever)
    }
}
