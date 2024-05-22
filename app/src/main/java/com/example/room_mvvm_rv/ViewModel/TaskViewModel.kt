package com.example.room_mvvm_rv.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.room_mvvm_rv.Model.Task
import com.example.room_mvvm_rv.Model.TaskDatabase
import com.example.room_mvvm_rv.Model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {
    private val repository: TaskRepository
// Live Data de tareas

    val allTask: LiveData<List<Task>>

    init {

        Log.i("viewModel", "CreateViewModel")
        val taskDao = TaskDatabase.getDatabase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask

    }

    // INSERT
    fun inserTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    // DELETE ALL
    fun deleteAllTask()= viewModelScope.launch {
        repository.deleteAllTask()
    }
        // DELETE WHERE
    fun deleteTask(task: Task)= viewModelScope.launch{

        repository.deletTask(task)
    }


    // UPDATE
    fun updateTask(task: Task)= viewModelScope.launch {

        repository.updateTask(task)
    }



    //  PARA SELECCIONAR LAS TAREAS


    private var selectedTask : MutableLiveData<Task?> = MutableLiveData()

    fun  selected(task: Task?){
        selectedTask.value = task
    }

    fun selectedItem(): MutableLiveData<Task?> = selectedTask

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel Destroy")
    }

}
