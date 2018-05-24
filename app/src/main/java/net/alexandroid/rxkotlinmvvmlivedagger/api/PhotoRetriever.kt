package net.alexandroid.rxkotlinmvvmlivedagger.api

import io.reactivex.Observable
import net.alexandroid.rxkotlinmvvmlivedagger.model.PhotoList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PhotoRetriever(okHttpClient: OkHttpClient) {
    private val service: PhotoAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://pixabay.com/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        service = retrofit.create(PhotoAPI::class.java)
    }

    fun getPhotosObservable(text: String): Observable<PhotoList> {
        return service.getPhotos(text)
    }
}
