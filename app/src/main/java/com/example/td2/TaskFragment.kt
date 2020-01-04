package com.example.td2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.task_fragment.view.*

class TaskFragment : Fragment() {

    private val taskViewModel by lazy {
        ViewModelProviders.of(this).get(TaskViewModel(taskAdapter)::class.java)
    }
    private val tasksRepository = TasksRepository()
    private val tasks = mutableListOf<Task>()
    private val taskAdapter = TaskAdapter(tasks)  {task : Task -> deleteTask(task)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container,false)
        view.tasks_recycler_view.adapter = taskAdapter
        view.tasks_recycler_view.layoutManager = LinearLayoutManager(context)
        tasksRepository.getTasks().observe(this, Observer {
            if (it != null) {
                tasks.clear()
                tasks.addAll(it)
                taskAdapter.notifyDataSetChanged()
            }
        })
        return view
    }

    private fun deleteTask(task:Task) {
        taskViewModel.deleteTask(task)
    }

}