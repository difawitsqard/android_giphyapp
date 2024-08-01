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
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rvGif) }
    private val gifs = mutableListOf<DataObject>()
    private val adapter: GifsAdapter by lazy { GifsAdapter(this, gifs) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getGifs()
    }

    private fun getGifs() {
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.setOnItemClickListener(object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, GifDetailActivity::class.java).apply {
                    putExtra("url", gifs[position].images.ogImage.url)
                    putExtra("title", gifs[position].title)
                    putExtra("source_tld", gifs[position].source_tld)
                }
                startActivities(arrayOf(intent))
            }
        })

        val apiService = RetrofitInstance.apiService
        val call = apiService.getGifs()
        call.enqueue(object : Callback<DataResult> {
            override fun onResponse(call: Call<DataResult>, response: Response<DataResult>) {
                response.body()?.let { data ->
                    //Log.d("MainActivity", data.res.toString())
                    gifs.addAll(data.res)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<DataResult>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ${t.message}")
            }
        })
    }
}