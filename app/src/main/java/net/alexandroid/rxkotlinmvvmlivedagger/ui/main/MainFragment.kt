package net.alexandroid.rxkotlinmvvmlivedagger.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.main_fragment.*
import net.alexandroid.rxkotlinmvvmlivedagger.MyApplication
import net.alexandroid.rxkotlinmvvmlivedagger.R
import net.alexandroid.rxkotlinmvvmlivedagger.shared.HasSharedElements
import net.alexandroid.utils.mylog.MyLog
import javax.inject.Inject

class MainFragment : androidx.fragment.app.Fragment(), View.OnClickListener, HasSharedElements {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    private var mainAdapter: MainAdapter? = null

    private val sharedElements: MutableMap<String, View> = mutableMapOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        MyApplication.component.inject(this)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this.activity, 2)

        initViewModel()
        setSearchListener()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.currentSearch.observe(this, Observer { query -> textView.text = query })

        viewModel.progressVisibility.observe(this, Observer { visibility ->
            progressBar.visibility = if (visibility!!) View.VISIBLE else View.GONE
        })

        viewModel.photosList.observe(this, Observer { list ->
            list?.let {
                mainAdapter = MainAdapter(it, this@MainFragment)
                recyclerView.adapter = mainAdapter
            }
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

    override fun hasReorderingAllowed(): Boolean = false

    //View.OnClickListener
    override fun onClick(v: View?) {
        val holder = v?.tag as MainAdapter.PhotoViewHolder

        sharedElements.clear()
        val transitionName = ViewCompat.getTransitionName(holder.photoItem)

        transitionName?.let {
            sharedElements[it] = holder.photoItem
        }

        val photo = mainAdapter?.getPhoto(holder.adapterPosition)

        photo?.let {
            MyLog.d("Click on: ${photo.previewURL}")
            val directions =
                    MainFragmentDirections.actionMainFragmentToItemFragment(photo, transitionName)
            v.findNavController().navigate(directions)
        }
    }

    //HasSharedElements
    override fun getSharedElements(): Map<String, View> = sharedElements
}
