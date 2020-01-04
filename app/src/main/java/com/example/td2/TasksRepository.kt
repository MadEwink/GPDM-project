package com.example.td2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.td2.network.Api
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TasksRepository {
    private val tasksService = Api.tasksService
    private val coroutineScope = MainScope()

    fun getTasks(): LiveData<List<Task>?> {
        val tasks = MutableLiveData<List<Task>?>()
        coroutineScope.launch { tasks.postValue(loadTasks()) }
        return tasks
    }

    suspend fun loadTasks(): List<Task>? {
        val tasksResponse = tasksService.getTasks()
        Log.e("loadTasks", tasksResponse.toString())
        return if (tasksResponse.isSuccessful) tasksResponse.body() else null
    }

    suspend fun deleteTask(task:Task) : Boolean {
        val response = tasksService.deleteTask(task.id)
        return response.isSuccessful
    }

    suspend fun createTask(task:Task) : Boolean {
        val response = tasksService.createTask(task)
        return response.isSuccessful
    }

    suspend fun editTask(task:Task) : Boolean {
        val response = tasksService.updateTask(task)
        return response.isSuccessful
    }
}