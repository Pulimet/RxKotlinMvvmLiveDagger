package net.alexandroid.rxkotlinmvvmlivedagger.dagger

import dagger.Module
import dagger.Provides
import net.alexandroid.rxkotlinmvvmlivedagger.api.PhotoRetriever
import okhttp3.OkHttpClient

@Module(includes = [(NetworkModule::class)])
class PhotosApiModule {
    @MyApplicationScope
    @Provides
    fun providePhotoRetriever(okHttpClient : OkHttpClient): PhotoRetriever {
        return PhotoRetriever(okHttpClient)
    }
}
