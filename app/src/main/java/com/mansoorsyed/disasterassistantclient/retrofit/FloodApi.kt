package com.mansoorsyed.disasterassistantclient.retrofit

import com.mansoorsyed.disasterassistantclient.model.Flood
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FloodApi {
    @get:GET("/flood/get-all")
    val allFloods: Call<List<Flood?>?>?

    @POST("/flood/save")
    fun save(@Body flood: Flood?): Call<Flood?>?
}