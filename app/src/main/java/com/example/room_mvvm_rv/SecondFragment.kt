package com.example.room_mvvm_rv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.room_mvvm_rv.Model.Task
import com.example.room_mvvm_rv.ViewModel.TaskViewModel
import com.example.room_mvvm_rv.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: TaskViewModel by activityViewModels()
    private var idTask: Int = 0
    private var taskSelected: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa la tarea seleccionada desde el ViewModel
        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                // Si hay una tarea seleccionada, llena los campos con los datos de la tarea
                binding.tvTitle.setText(it.title)
                binding.tvDescription.setText(it.taskDescription)
                binding.tvDate.setText(it.date)
                binding.tvpriority.setText(it.priority.toString())
                binding.cbState.isChecked = it.state
                idTask = it.id
                taskSelected = it
            }
        })

        // Configura el click listener para el botón de guardado
        binding.save.setOnClickListener {
            if (idTask == 0) {
                saveNewTask() // Guarda una nueva tarea si idTask es 0
            } else {
                updateTask() // Actualiza la tarea existente de otro modo
            }
        }
    }

    // Método para guardar una nueva tarea
    private fun saveNewTask() {
        val title = binding.tvTitle.text.toString()
        val description = binding.tvDescription.text.toString()
        val date = binding.tvDate.text.toString()
        val priority = binding.tvpriority.text.toString().toInt()
        val state = binding.cbState.isChecked

        // Verifica que todos los campos estén llenos
        if (title.isEmpty() || description.isEmpty() || date.isEmpty()) {
            Toast.makeText(context, "Debes añadir todos los datos", Toast.LENGTH_LONG).show()
            return
        }

        // Crea una nueva tarea y la inserta en el ViewModel
        val newTask = Task(
            title = title,
            taskDescription = description,
            date = date,
            priority = priority,
            state = state
        )
        viewModel.inserTask(newTask)
        Toast.makeText(context, "Tarea añadida exitosamente", Toast.LENGTH_SHORT).show()
        navigateToFirstFragment()
    }

    // Método para actualizar una tarea existente
    private fun updateTask() {
        val title = binding.tvTitle.text.toString()
        val description = binding.tvDescription.text.toString()
        val date = binding.tvDate.text.toString()
        val priority = binding.tvpriority.text.toString().toInt()
        val state = binding.cbState.isChecked

        // Verifica que todos los campos estén llenos
        if (title.isEmpty() || description.isEmpty() || date.isEmpty()) {
            Toast.makeText(context, "Debes añadir todos los datos", Toast.LENGTH_LONG).show()
            return
        }

        // Crea una tarea actualizada y la actualiza en el ViewModel
        val updatedTask = Task(
            id = idTask,
            title = title,
            taskDescription = description,
            date = date,
            priority = priority,
            state = state
        )
        viewModel.updateTask(updatedTask)
        Toast.makeText(context, "Tarea actualizada exitosamente", Toast.LENGTH_SHORT).show()
        navigateToFirstFragment()
    }

    // Navega de vuelta al `FirstFragment` y limpia la tarea seleccionada en el ViewModel
    private fun navigateToFirstFragment() {
        viewModel.selected(null)
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
}