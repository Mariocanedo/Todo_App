package com.example.room_mvvm_rv.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task (

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id:  Int =0,
    var title: String,
    var taskDescription: String,
    var date : String,
    var priority :Int,
    var state :Boolean


)