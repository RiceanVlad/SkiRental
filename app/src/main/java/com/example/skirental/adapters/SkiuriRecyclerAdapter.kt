package com.example.skirental.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.skirental.R
import com.example.skirental.models.PerecheSki
import com.google.firebase.storage.FirebaseStorage

class SkiuriRecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<PerecheSki> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkiuriViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SkiuriViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<PerecheSki>){
        items = blogList
    }

    inner class SkiuriViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        val blogImage : ImageView = itemView.findViewById<ImageView>(R.id.blog_image)
        val blogTitle : TextView = itemView.findViewById<TextView>(R.id.blog_title)
        val blogAuthor : TextView = itemView.findViewById<TextView>(R.id.blog_author)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(perecheSkiuri: PerecheSki){

            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            var imagesRef = storageRef.child("schiuri/${perecheSkiuri.imagine}.jpg")

            blogTitle.setText(perecheSkiuri.firma)
            blogAuthor.setText(perecheSkiuri.descriere)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            imagesRef.downloadUrl.addOnSuccessListener {
                Glide.with(itemView.context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load(it)
                        .into(blogImage)
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}