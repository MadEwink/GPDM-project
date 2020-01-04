package com.example.td2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_taskform.*
import kotlinx.android.synthetic.main.item_task.*

class TaskFormActivity : AppCompatActivity() {

    private val taskViewModel by lazy {
        ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taskform)
        form_title.setText(intent.getStringExtra("title")?:"")
        form_description.setText(intent.getStringExtra("description")?:"")
        val id = intent.getStringExtra("id")
        if (id == null)
            confirm_form.setOnClickListener {createTask(); backToMain()}
        else
            confirm_form.setOnClickListener {editTask(id); backToMain()}
        back_to_main.setOnClickListener {backToMain()}
    }

    private fun createTask(){
        val title = form_title.text.toString()
        val description = form_description.text.toString()
        if (title != "") {
            val id = ""+title.hashCode()+description.hashCode()
            val task : Task
            if (description == "")
                task = Task(id,title)
            else
                task = Task(id,title,description)
            taskViewModel.createTask(task)
        }
    }

    private fun editTask(id : String){
        val title = form_title.text.toString()
        val description = form_description.text.toString()
        if (title != "") {
            val task : Task
            if (description == "")
                task = Task(id, title)
            else
                task = Task(id, title, description)
            taskViewModel.editTask(task)
        }
    }

    private fun backToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
