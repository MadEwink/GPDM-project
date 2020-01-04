package com.example.td2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.task_fragment.view.*

class TaskFragment : Fragment() {

    private val tasks = mutableListOf(
        Task(id=0, title="Faire Cuire une Banane", description = "La banane se mange cuite au four à 259.87°F"),
        Task(id=1, title="Finir Android"),
        Task(id=2, title="Avoir une vie"),
        Task(id=3, title="Finir son année", description = "Android, Gestion de Projet, Jade, Warbot, PFE"),
        Task(id=4, title="Trouver un stage", description = "6 mois à l'international")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = TaskAdapter(tasks)
        adapter.onDeleteClickListener = { task -> tasks.remove(task)} //take arg and see
        val view = inflater.inflate(R.layout.task_fragment, container,false)
        view.tasks_recycler_view.adapter = adapter
        view.tasks_recycler_view.layoutManager = LinearLayoutManager(context)
        return view
    }
}