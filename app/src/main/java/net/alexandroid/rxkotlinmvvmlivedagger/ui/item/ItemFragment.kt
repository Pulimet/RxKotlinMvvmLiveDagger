package net.alexandroid.rxkotlinmvvmlivedagger.ui.item


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.alexandroid.rxkotlinmvvmlivedagger.R
import net.alexandroid.utils.mylog.MyLog

class ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val args = ItemFragmentArgs.fromBundle(arguments)
        MyLog.d("Photo url: ${args.photo.previewURL}")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_fragment, container, false)
    }


}
