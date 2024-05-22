package com.example.room_mvvm_rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.room_mvvm_rv.Model.Task
import com.example.room_mvvm_rv.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskVH>(){

    // lista de tareas
       private var mlistTask = listOf<Task>()

    // un elemento para seleccionart
    private val selectedTask =MutableLiveData<Task>()

    fun selectedItem(): LiveData<Task> = selectedTask


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
    return  TaskVH(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = mlistTask[position]
        holder.bind(task)
    }
    override fun getItemCount(): Int =
      mlistTask.size


 fun updateAdapter(listTask: List<Task>){
     mlistTask = listTask
     notifyDataSetChanged()
 }



    inner class TaskVH(private val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root),
            View.OnClickListener{

                fun bind(task:Task){
                    binding.tvTitle.text= task.title
                    binding.tvDescription.text= task.taskDescription
                    binding.tvDate.text= task.date
                    binding.cbState.isChecked = task.state
                    itemView.setOnClickListener(this)
                }

        override fun onClick(v: View?) {
            selectedTask.value = mlistTask[adapterPosition]
        }


    }


}