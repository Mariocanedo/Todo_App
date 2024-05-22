package com.example.room_mvvm_rv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_mvvm_rv.ViewModel.TaskViewModel
import com.example.room_mvvm_rv.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    // Vista vinculada con el FragmentFirstBinding
    private lateinit var binding: FragmentFirstBinding

    // ViewModel compartido entre Fragmentos
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instanciamos el adapter y se lo pasamos al RV
        val adapter = TaskAdapter()
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = LinearLayoutManager(context)

        // Esto esta observando al objeto expuesto en el viewModel
        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateAdapter(it)
            }
        })



        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("ITEM SELECTED", it.title)
                viewModel.selected(it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
    }


}