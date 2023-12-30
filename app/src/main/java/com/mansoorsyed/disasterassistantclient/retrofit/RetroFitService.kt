package com.mansoorsyed.disasterassistantclient.retrofit

import android.content.Context
import com.google.gson.Gson
import io.github.cdimascio.dotenv.dotenv
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitService public constructor(context: Context) {
    var retroFit: Retrofit? = null

    private var dotenv = dotenv {
        directory = "/assets"
        filename = "env"
    }
    private val ipAddress: String = dotenv["IP"].toString()

    init {
        initializeRetrofit(context)
    }

    private fun initializeRetrofit(context: Context) {
        retroFit = Retrofit.Builder()
            .baseUrl("http://$ipAddress:9000")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okhttpClient(context))
            .build()
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}