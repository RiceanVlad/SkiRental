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
import com.example.skirental.models.Ochelari

class OchelariRecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Ochelari> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OchelariViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is OchelariViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<Ochelari>){
        items = blogList
    }

    inner class OchelariViewHolder constructor(
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

        fun bind(ochelari: Ochelari){

            blogTitle.setText(ochelari.descriere)
            blogAuthor.setText(ochelari.firma)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(ochelari.imagine)
                .into(blogImage)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}