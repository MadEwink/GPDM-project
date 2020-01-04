package com.example.td2

import com.squareup.moshi.Json

data class Task (
    @field:Json(name = "id")
    val id : String,
    @field:Json(name = "title")
    var title : String,
    @field:Json(name = "description")
    var description : String =  "Empty task"
    )
{

}