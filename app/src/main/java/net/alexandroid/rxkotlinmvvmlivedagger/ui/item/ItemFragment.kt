package net.alexandroid.rxkotlinmvvmlivedagger.ui.item


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fragment.*
import net.alexandroid.rxkotlinmvvmlivedagger.R
import net.alexandroid.utils.mylog.MyLog

class ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = ItemFragmentArgs.fromBundle(arguments)
        MyLog.d("Photo url: ${args.photo.webformatURL}")

        args.photo.webformatURL.let {
            Picasso.get().load(args.photo.webformatURL).into(imageView)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
