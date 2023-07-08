package com.istea.app31

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall {
    fun getRandomJoke(category:String, callback: (ChuckNorrisJoke?) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ChuckNorrisApi::class.java)
        api.getRandomJoke(category).enqueue(object : Callback<ChuckNorrisJoke> {
            override fun onResponse(call: Call<ChuckNorrisJoke>, response: Response<ChuckNorrisJoke>) {

                if (response.isSuccessful) {
                    val joke : ChuckNorrisJoke? = response.body()
                    callback(joke)
                }
                else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<ChuckNorrisJoke>, t: Throwable) {
                callback(null)
            }
        })
    }
}
