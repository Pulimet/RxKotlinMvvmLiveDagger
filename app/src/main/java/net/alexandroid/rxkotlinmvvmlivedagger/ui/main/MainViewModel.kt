package net.alexandroid.rxkotlinmvvmlivedagger.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


class MainViewModel(private val photoRetriever: PhotoRetriever) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _currentSearch: MutableLiveData<String> = MutableLiveData()
    private val _photosList: MutableLiveData<List<Photo>> = MutableLiveData()
    private val _progressVisibility: MutableLiveData<Boolean> = MutableLiveData()

    private var tempQuery: String? = null

    val currentSearch: LiveData<String>
        get() = _currentSearch
    val photosList: LiveData<List<Photo>>
        get() = _photosList
    val progressVisibility: LiveData<Boolean>
        get() = _progressVisibility

    fun searchObservableReady(searchObservable: Observable<String>) {
        disposables.add(
                searchObservable.debounce(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { text ->
                                    _currentSearch.value = text
                                    onNewSearchQuery(text)
                                },
                                { t -> throw OnErrorNotImplementedException(t) }))
    }

    private fun onNewSearchQuery(text: String?) {
        MyLog.d("New query: $text")
        if (text == null || text.length < 3 || tempQuery.equals(text)) {
            return
        }
        tempQuery = text
        _progressVisibility.value = true
        disposables.add(
                photoRetriever.getPhotosObservable(text)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { list: PhotoList? ->
                            MyLog.d("Size: " + list?.hits?.size)
                            onPhotosReceived(list)
                        })
    }

    private fun onPhotosReceived(list: PhotoList?) {
        _progressVisibility.value = false
        if (list?.hits?.size != null) {
            _photosList.value = list.hits
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}
