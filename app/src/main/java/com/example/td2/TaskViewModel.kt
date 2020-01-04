package com.example.td2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(val taskAdapter: TaskAdapter) : ViewModel() {

    private val repository = TasksRepository()
    private val tasks = mutableListOf<Task>()

    fun deleteTask(task : Task)
    {
        viewModelScope.launch {
            if (repository.deleteTask(task)) {
                tasks.remove(task)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    fun createTask(task: Task)
    {
        viewModelScope.launch {
            if (repository.createTask(task)) {
                tasks.add(task)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    fun editTask(task: Task)
    {
        viewModelScope.launch {
            if (repository.createTask(task)) {
                var task_to_update = tasks.find { oldtask -> oldtask.id == task.id }
                task_to_update?.title = task.title
                task_to_update?.description = task.description
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    fun loadTasks()
    {
        viewModelScope.launch {
            val repository_tasks = repository.loadTasks()
            if (repository_tasks != null) {
                tasks.clear()
                tasks.addAll(repository_tasks)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

}