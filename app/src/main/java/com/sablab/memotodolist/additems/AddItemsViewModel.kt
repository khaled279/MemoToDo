package com.sablab.memotodolist.additems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sablab.memotodolist.dataclasses.Memo
import com.sablab.memotodolist.dataclasses.ToDoTask
import com.sablab.memotodolist.repos.MemoTodoRepo
import kotlinx.coroutines.launch

class AddItemsViewModel(private val repo:MemoTodoRepo): ViewModel() {
   var task:ToDoTask? = null
   var note:Memo? = null

   fun makeTask(description:String , importance: Boolean){
      task = ToDoTask(description = description , important = importance)
   }

   class AddItemsViewModelFactory(private  val repository:MemoTodoRepo): ViewModelProvider.Factory{
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         if (modelClass.isAssignableFrom(AddItemsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AddItemsViewModel(repository) as T
         }
         throw IllegalArgumentException("COULDN't IDENTIFY VIewMODEL")

      }

   }

   fun insertTask(task: ToDoTask) {
      viewModelScope.launch {
         repo.insertTask(task)
      }
   }
   fun createNote(textNote:String){
      note = Memo(noteText = textNote)
   }
   fun insertNote(noteToSave:Memo){
      viewModelScope.launch {
         repo.insertNote(noteToSave)
      }

}
   }
