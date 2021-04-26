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
import com.example.skirental.models.Produs
import com.google.firebase.storage.FirebaseStorage

class ClapariRecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Produs> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ClapariViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ClapariViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<Produs>){
        items = blogList
    }

    inner class ClapariViewHolder constructor(
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

        fun bind(clapari: Produs){

            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            var imagesRef = storageRef.child("clapari/${clapari.imagine}.jpg")

            blogTitle.setText(clapari.descriere)
            blogAuthor.setText(clapari.firma)

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