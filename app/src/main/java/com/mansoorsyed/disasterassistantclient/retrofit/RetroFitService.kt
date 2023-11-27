package com.mansoorsyed.disasterassistantclient.retrofit

import com.google.gson.Gson
import io.github.cdimascio.dotenv.dotenv
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitService public constructor() {
    var retroFit: Retrofit? = null

    private var dotenv = dotenv {
        directory = "/assets"
        filename = "env"
    }
    private val ipAddress: String? = dotenv["IP"].toString()

    init {
        initializeRetrofit()
    }

    private fun initializeRetrofit() {
        retroFit = Retrofit.Builder()
            .baseUrl("http://$ipAddress:9000")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }
}