package com.sablab.memotodolist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sablab.memotodolist.dataclasses.Memo
import com.sablab.memotodolist.dataclasses.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoToDoDao {
    @Query("SELECT * FROM notes Order by id DESC")
    fun getAllNotes(): Flow<List<Memo>>

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks():Flow<List<ToDoTask>>

    @Query("SELECT * FROM tasks ORDER BY important DESC")
    fun getTasksPrioritized(): Flow<List<ToDoTask>>

    @Insert(entity = ToDoTask::class)
    suspend fun insertTask(task: ToDoTask)

    @Insert(entity = Memo::class)
    suspend fun insertNote(note:Memo)

    @Query("DELETE from notes ")
    suspend fun deleteAllNotes()

    @Query("DELETE from tasks")
    suspend fun deleteAllTasks()

    @Update(entity =ToDoTask::class)
    suspend fun updateTask(task: ToDoTask)

    @Update(entity = Memo::class)
    suspend fun updatenote(note:Memo)

}