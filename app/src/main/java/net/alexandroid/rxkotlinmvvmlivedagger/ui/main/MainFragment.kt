package net.alexandroid.rxkotlinmvvmlivedagger.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.main_fragment.*
import net.alexandroid.rxkotlinmvvmlivedagger.MyApplication
import net.alexandroid.rxkotlinmvvmlivedagger.R
import net.alexandroid.rxkotlinmvvmlivedagger.api.PhotoRetriever
import javax.inject.Inject

class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var photoRetriever: PhotoRetriever

    private lateinit var viewModel: MainViewModel

    var mainAdapter: MainAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        MyApplication.component.inject(this)

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.photoRetriever = photoRetriever
        setSearchListener()

        viewModel.getCurrentSearch().observe(this, Observer { query -> textView.text = query })

        viewModel.getphotosList().observe(this, Observer { list ->
            mainAdapter = list?.let {
                MainAdapter(it, this@MainFragment)
            }
            recyclerView.adapter = mainAdapter
        })
    }

    private fun setSearchListener() {
        val searchObservable = Observable.create(ObservableOnSubscribe<String> { subscriber ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = subscriber.onNext(s.toString())
            })
        })

        viewModel.searchObservableReady(searchObservable)
    }


    //View.OnClickListener
    override fun onClick(v: View?) {
        val holder = v?.tag as MainAdapter.PhotoViewHolder
        val photo = mainAdapter?.getPhoto(holder.adapterPosition)

        photo?.let {
            //mPresenter.onImageClick(it)
        }
    }

}
