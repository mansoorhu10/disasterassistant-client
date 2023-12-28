package com.mansoorsyed.disasterassistantclient.retrofit

import com.mansoorsyed.disasterassistantclient.model.Token
import com.mansoorsyed.disasterassistantclient.model.UserLogin
import com.mansoorsyed.disasterassistantclient.model.UserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/v1/auth/register")
    fun register(@Body userRegister: UserRegister?) : Call<Token?>?

    @POST("/api/v1/auth/authenticate")
    fun login(@Body userLogin: UserLogin?) : Call<Token?>?

}