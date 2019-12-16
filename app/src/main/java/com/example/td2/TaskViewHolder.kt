package com.example.td2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(task:String) {
        itemView.task_title.text = task
    }
}
