package com.example.td2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(val tasks: List<Task>, private val onDeleteClickListener: (Task)->Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>()  {
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task:Task) {
            itemView.task_title.text = task.title
            itemView.task_description.text = task.description
            itemView.task_edit.setOnClickListener{onEditClickListener(task)}
            itemView.task_delete.setOnClickListener{onDeleteClickListener(task)}
        }
        private fun onEditClickListener(task: Task) {
            val intent = Intent(itemView.context, TaskFormActivity::class.java)
            intent.putExtra("id", task.id)
            intent.putExtra("title", task.title)
            intent.putExtra("description", task.description)
            itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false))
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }
}