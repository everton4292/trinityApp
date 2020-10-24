package com.everton.trinitychallengeapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everton.trinitychallengeapp.R
import com.everton.trinitychallengeapp.data.model.Photo
import kotlinx.android.synthetic.main.each_item.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val photosList: MutableList<Photo> = mutableListOf()
    lateinit var context: Context

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) {
            val image = "${photo.img_src}".replace("http", "https")
            Glide.with(itemView.context).load(image).into(itemView.roverImage)
            itemView.textViewItemName.text = photo.rover?.name
            itemView.textViewItemDate.text = photo.earth_date
            itemView.textViewCameraName.text = photo.camera?.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context
        val cellForRow =
            LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return HomeViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return this.photosList.size
    }

    fun updateData(photos: List<Photo>){
        photosList.clear()
        photosList.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Photo {
        return this.photosList[position]
    }
}