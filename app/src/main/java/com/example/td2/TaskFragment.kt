package com.example.td2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.task_fragment.view.*

class TaskFragment : Fragment() {
    private val tasks = listOf("Task 1", "Task 2", "Task 3")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = TaskAdapter(tasks)
        val view = inflater.inflate(R.layout.task_fragment, container,false)
        view.tasks_recycler_view.adapter = adapter
        view.tasks_recycler_view.layoutManager = LinearLayoutManager(context)
        return view
    }
}