package com.example.td2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.td2.network.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_taskform.*
import kotlinx.android.synthetic.main.item_task.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TaskFormActivity : AppCompatActivity() {

    private val taskViewModel by lazy {
        ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    private val coroutineScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taskform)

        var id : String? = intent?.data.toString()
        if (id != "null")
        {
            id = id?.substringAfterLast('/')
            coroutineScope.launch {
                val tasks = Api.INSTANCE.tasksService.getTasks().body()
                val task = tasks?.find { task : Task -> task.id == id}
                form_title.setText(task?.title)
                form_description.setText(task?.description)
            }
        }
        else {
            id = intent.getStringExtra("id")
            form_title.setText(intent.getStringExtra("title") ?: "")
            form_description.setText(intent.getStringExtra("description") ?: "")
        }
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
