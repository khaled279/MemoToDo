package com.sablab.memotodolist.notesfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sablab.memotodolist.MemoApplication
import com.sablab.memotodolist.R

class NotesFragment:Fragment() {

    var viewModel:NotesViewModel? = null
    var toTasks:TextView? = null
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmet_notes,container,false)
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val notesViewModel: NotesViewModel by viewModels{
            NotesViewModel.NotesViewModelFactory((activity?.application as MemoApplication).repostory)
        }
        viewModel = notesViewModel
        NavHostFragment.findNavController(this).popBackStack(R.id.addNewItems,true)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NotesAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2 , RecyclerView.VERTICAL )
        notesViewModel!!.allNotes.observe(viewLifecycleOwner, androidx.lifecycle.Observer { notes ->
            // Update the cached copy of the words in the adapter.
            notes?.let { adapter.submitList(it) }
        })
        val floatingButton:FloatingActionButton = view.findViewById(R.id.fab)
        floatingButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_notesFragment_to_addNewNotesFragment)
        }
        toTasks = view.findViewById(R.id.to_tasks)
        toTasks?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_notesFragment_to_tasksFragment)
        }
    }
}