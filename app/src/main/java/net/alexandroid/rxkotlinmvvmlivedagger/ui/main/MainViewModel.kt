package net.alexandroid.rxkotlinmvvmlivedagger.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.schedulers.Schedulers
import net.alexandroid.rxkotlinmvvmlivedagger.api.PhotoRetriever
import net.alexandroid.rxkotlinmvvmlivedagger.model.Photo
import net.alexandroid.rxkotlinmvvmlivedagger.model.PhotoList
import net.alexandroid.utils.mylog.MyLog
import java.util.concurrent.TimeUnit


class MainViewModel: ViewModel() {

    val disposables = CompositeDisposable()

    var photoRetriever: PhotoRetriever? = null

    val currentSearch: MutableLiveData<String> = MutableLiveData()
    fun getCurrentSearch(): LiveData<String> = currentSearch

    val photosList: MutableLiveData<List<Photo>> = MutableLiveData()
    fun getphotosList(): LiveData<List<Photo>> = photosList

    fun searchObservableReady(searchObservable: Observable<String>) {
        disposables.add(
                searchObservable.debounce(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { text ->
                                    currentSearch.value = text
                                    onNewSearchQuery(text)
                                },
                                { t -> throw OnErrorNotImplementedException(t) }))
    }

    private fun onNewSearchQuery(text: String?) {
        MyLog.d("New query: $text")
        if (text == null || text.length < 3 || photoRetriever == null) {
            return
        }
        //mView?.get()?.setProgressBarVisible(true)
        disposables.add(
                photoRetriever!!.getPhotosObservable(text)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ list: PhotoList? ->
                            MyLog.d("Size: " + list?.hits?.size)
                            onPhotosReceived(list)
                        }))
    }

    private fun onPhotosReceived(list: PhotoList?) {
        //mView?.get()?.setProgressBarVisible(false)
        if (list?.hits?.size != null) {
            photosList.value = list.hits
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}
