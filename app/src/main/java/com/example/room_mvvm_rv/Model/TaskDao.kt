package com.example.room_mvvm_rv.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    // UNA TAREA

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserTask(task:Task)

    // LISTADO DE TAREAS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTask(taskList :List<Task>)

    //ACTUALIZAR
    @Update
    suspend fun updateTask(task:Task)


    //ELIMINAR una tarea recibe en el constructor una tarea
    @Delete
    suspend fun deleteTask(task:Task)

   @Query("DELETE FROM task_table")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM task_table WHERE id = :mId")
    fun getTaskById(mId: Int):LiveData<Task>

    @Query("SELECT * FROM task_table")
    fun getAllTask(): LiveData<List<Task>>

}