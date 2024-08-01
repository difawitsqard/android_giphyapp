package com.difawitsqard.giphyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class GifDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_detail)

        val imageView = findViewById<ImageView>(R.id.ivGifDetail)
        val tvGifTitle = findViewById<TextView>(R.id.tvGifTitle)
        val tvGifSource = findViewById<TextView>(R.id.tvGifSource)
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        var source_tld = intent.getStringExtra("source_tld")
        source_tld = if (source_tld.isNullOrEmpty()) "unknown" else source_tld

        tvGifTitle.text = title
        tvGifSource.text = "source: " + source_tld
        Glide.with(this).load(url).into(imageView)
    }
}