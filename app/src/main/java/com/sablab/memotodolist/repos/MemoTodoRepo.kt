package com.sablab.memotodolist.repos

import androidx.annotation.WorkerThread
import com.sablab.memotodolist.database.MemoToDoDao
import com.sablab.memotodolist.dataclasses.Memo
import com.sablab.memotodolist.dataclasses.ToDoTask
import kotlinx.coroutines.flow.Flow

class MemoTodoRepo(private val dao:MemoToDoDao ) {
    val allNotes: Flow<List<Memo>> = dao.getAllNotes()
    val allTasks: Flow<List<ToDoTask>> = dao.getTasksPrioritized()
    @WorkerThread
    suspend fun insertNote(note:Memo){
        dao.insertNote(note)

    }

    @WorkerThread
    suspend fun insertTask(task: ToDoTask){
        dao.insertTask(task)
    }


}