package com.difawitsqard.giphyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.difawitsqard.giphyapp.adapter.GifsAdapter
import com.difawitsqard.giphyapp.model.DataObject
import com.difawitsqard.giphyapp.model.DataResult
import com.difawitsqard.giphyapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getGifs()
    }

    private fun getGifs() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvGif)
        val gifs = mutableListOf<DataObject>()
        val adapter = GifsAdapter(this, gifs)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.setOnItemClickListener(object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, GifDetailActivity::class.java)
                intent.putExtra("url", gifs[position].images.ogImage.url)
                intent.putExtra("title", gifs[position].title)
                intent.putExtra("source_tld", gifs[position].source_tld)
                startActivities(arrayOf(intent))
            }
        })

        val apiService = RetrofitInstance.apiService
        val call = apiService.getGifs()
        call.enqueue(object : Callback<DataResult> {
            override fun onResponse(call: Call<DataResult>, response: Response<DataResult>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Log.d("MainActivity", "onResponse: noResponse")
                    }
                    // add all data to gifs
                    gifs.addAll(data!!.res)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<DataResult>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ${t.message}")
            }
        })
    }
}