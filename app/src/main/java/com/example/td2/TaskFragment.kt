package com.example.td2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.task_fragment.view.*

class TaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container,false)
        val taskViewModel by lazy {
            ViewModelProviders.of(this).get(TaskViewModel::class.java)
        }
        view.tasks_recycler_view.adapter = taskViewModel.taskAdapter
        view.tasks_recycler_view.layoutManager = LinearLayoutManager(context)
        return view
    }

}