package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import com.example.pixabay.databinding.ActivityMainBinding
import com.example.pixabay.model.PixaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = PixaAdapter()
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()


    }

    private fun initClicker() {
        with(binding) {
            btnSearch.setOnClickListener {
                doRequest()
            }
            nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val nv = v as NestedScrollView
                if (scrollY == nv.getChildAt(0).measuredHeight - v.measuredHeight) {
                    page++
                    progressBar.isVisible = true
                    doRequest()
                }
            }
        }
    }


    private fun ActivityMainBinding.doRequest() {
        RetrofitService().api.searchImage(keyWord = searchEt.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    response.body()?.hits?.let {
                        adapter.addList(it)
                        binding.progressBar.isVisible = false
                    }
                    Log.e("ololo", "onResponse: ${response.body()?.hits?.get(0)?.largeImageURL} ")
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }
            })


        recyclerView.adapter = adapter
    }
}