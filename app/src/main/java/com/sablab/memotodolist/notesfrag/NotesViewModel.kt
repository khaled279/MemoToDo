package com.sablab.memotodolist.notesfrag

import androidx.lifecycle.*
import com.sablab.memotodolist.dataclasses.Memo
import com.sablab.memotodolist.repos.MemoTodoRepo
import kotlinx.coroutines.launch

class NotesViewModel(private  val repo:MemoTodoRepo):ViewModel() {
    val allNotes:LiveData<List<Memo>> = repo.allNotes.asLiveData()
    fun insertNote(note:Memo){
        viewModelScope.launch {
            repo.insertNote(note)
        }
    }
    class NotesViewModelFactory(private  val repository:MemoTodoRepo): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("COULDN't IDENTIFY VIewMODEL")

        }

    }

}
