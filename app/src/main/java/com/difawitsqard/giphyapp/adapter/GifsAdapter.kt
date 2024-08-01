package com.difawitsqard.giphyapp.adapter

// Created : 01/08/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.difawitsqard.giphyapp.R
import com.difawitsqard.giphyapp.model.DataObject

class GifsAdapter(val context: Context, val gifs:List<DataObject>) : RecyclerView.Adapter<GifsAdapter.ViewHolder>() {

    lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false), mListener)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = gifs[position]
        Glide.with(context).load(data.images.ogImage.url).into(holder.imageView)
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.ivGif)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(adapterPosition)
                }
            }
        }
    }
}

