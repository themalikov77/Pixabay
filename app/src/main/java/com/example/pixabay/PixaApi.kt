package com.example.pixabay

import com.example.pixabay.model.PixaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun searchImage(
        @Query("q") keyWord: String,
        @Query("page") page :Int,
        @Query ("per_page") per_page:Int = 3,
        @Query("key") key: String = "32443532-aa20c165473a3dbfe2b916feb"
    ): Call<PixaModel>
}