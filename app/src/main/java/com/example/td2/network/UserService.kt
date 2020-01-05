package com.example.td2.network

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("users/info")
    suspend fun getInfo(): Response<UserInfo>
    @POST("users/login")
    suspend fun login(@Body user: LoginForm): Response<TokenResponse>
}