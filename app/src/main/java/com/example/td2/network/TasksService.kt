package com.example.td2.network

import com.example.td2.Task
import retrofit2.Response
import retrofit2.http.GET

interface TasksService {
    @GET("tasks")
    suspend fun getTasks(): Response<List<Task>>
}