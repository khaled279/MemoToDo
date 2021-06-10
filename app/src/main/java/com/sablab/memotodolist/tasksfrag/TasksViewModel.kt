package com.sablab.memotodolist.tasksfrag

import androidx.lifecycle.*
import com.sablab.memotodolist.dataclasses.ToDoTask
import com.sablab.memotodolist.repos.MemoTodoRepo
import kotlinx.coroutines.launch

class TasksViewModel(private  val repo:MemoTodoRepo):ViewModel() {
    val allTasks: LiveData<List<ToDoTask>> = repo.allTasks.asLiveData()
    fun insertTask(task: ToDoTask){
        viewModelScope.launch {
            repo.insertTask(task)
        }
    }
    class TasksViewModelFactory(private  val repository:MemoTodoRepo): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TasksViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return TasksViewModel(repository) as T
            }
            throw IllegalArgumentException("COULDN't IDENTIFY VIewMODEL")

        }

    }


}