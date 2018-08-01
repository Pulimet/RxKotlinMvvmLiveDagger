package net.alexandroid.rxkotlinmvvmlivedagger.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.alexandroid.rxkotlinmvvmlivedagger.R
import net.alexandroid.rxkotlinmvvmlivedagger.model.Photo
import net.alexandroid.utils.mylog.MyLog

class MainAdapter(private var photos: List<Photo>, private var clickListener: View.OnClickListener) :
        androidx.recyclerview.widget.RecyclerView.Adapter<MainAdapter.PhotoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_item, parent, false))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val photo = photos[position]
        holder.tags.text = photo.tags.take(15)
        holder.likes.text = photo.likes.toString()
        holder.favorites.text = photo.favorites.toString()

        MyLog.d("$position - Url: ${photo.previewURL}")

        Glide.with(holder.itemView.context)
                .load(photo.previewURL)
                .into(holder.photoItem)
    }

    fun getPhoto(adapterPosition: Int): Photo {
        return photos[adapterPosition]
    }


    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tags: TextView
        var likes: TextView
        var favorites: TextView
        var photoItem: ImageView

        init {
            itemView.setOnClickListener(clickListener)
            itemView.tag = this
            tags = itemView.findViewById(R.id.tags) as TextView
            likes = itemView.findViewById(R.id.likes) as TextView
            favorites = itemView.findViewById(R.id.favorites) as TextView
            photoItem = itemView.findViewById(R.id.photo_item) as ImageView
        }
    }


}