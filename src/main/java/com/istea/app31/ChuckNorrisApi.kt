package com.istea.app31

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {
    @GET("jokes/random")
    fun getRandomJoke(@Query("category") category:String): Call<ChuckNorrisJoke>
}
