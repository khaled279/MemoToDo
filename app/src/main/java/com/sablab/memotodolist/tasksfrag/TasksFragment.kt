package com.sablab.memotodolist.tasksfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sablab.memotodolist.MemoApplication
import com.sablab.memotodolist.R

class TasksFragment:Fragment() {
    lateinit var  recyclerView: RecyclerView
    lateinit var floatingButton : FloatingActionButton
    var viewModel: TasksViewModel? = null
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks,container,false)
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasksViewModel: TasksViewModel by viewModels{
            TasksViewModel.TasksViewModelFactory((activity?.application as MemoApplication).repostory)
        }
        viewModel = tasksViewModel
        recyclerView = view.findViewById(R.id.tasks_recyclerview)
        floatingButton = view.findViewById(R.id.add_task_fb)
        val adapter = TasksAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        tasksViewModel!!.allTasks.observe(viewLifecycleOwner, androidx.lifecycle.Observer { tasks ->
            // Update the cached copy of the words in the adapter.
            tasks?.let { adapter.submitList(it) }
        })
    }

}