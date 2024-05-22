package com.example.room_mvvm_rv.Model

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao){

    // Este value va a contener todos los datos desde la BBDD

    val listAllTask :LiveData<List<Task>> = taskDao.getAllTask()

    // insert
    suspend fun  insertTask( task: Task){
        taskDao.inserTask(task)

    }

    // delete una tarea sólo 1

    suspend fun deletTask(task: Task){
        taskDao.deleteTask(task)
    }

    // Borra todos los datos que hay!
    suspend fun  deleteAllTask(){
        taskDao.deleteAllTask()
    }

    // actualizar
    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }



}


