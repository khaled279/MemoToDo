package com.sablab.memotodolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sablab.memotodolist.dataclasses.Memo
import com.sablab.memotodolist.dataclasses.ToDoTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Memo::class,ToDoTask::class] , version = 1 , exportSchema = false)
abstract class MemoTodoDataBase:RoomDatabase() {
    abstract fun memoToDoDao():MemoToDoDao

    companion object {
        @Volatile
        private  var dbInstance:MemoTodoDataBase? = null

        fun getDataBaseInstance(context: Context , scope:CoroutineScope):MemoTodoDataBase{
            return dbInstance ?: synchronized(this){
                val instance = Room.databaseBuilder(context,MemoTodoDataBase::class.java
                    ,"memo_task_database").addCallback(MemotoDoCallBack(scope)).build()
                dbInstance = instance
                instance
            }
        }

    }
    class MemotoDoCallBack(private val scope: CoroutineScope):RoomDatabase.Callback() {
        @Override
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            dbInstance?.let { database ->
                scope.launch {
                    populateDB(database.memoToDoDao())
                }
            }
        }
        suspend fun populateDB(dao:MemoToDoDao){
            dao.insertNote(Memo( noteText = "FIRST NOTE INSHALL THIS WILL WORK AS intended ," +
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
                    +
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
                    +
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
            ))
            dao.insertNote(Memo( noteText = "FIRST NOTE INSHALL THIS WILL WORK AS intended"+
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"

                    ))
            dao.insertNote(Memo( noteText = "FIRST NOTE INSHALL THIS WILL WORK AS intended"+
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
                    +
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
                    +
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
                    +
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
                    +
                    "\n FIRST NOTE INSHALL THIS WILL WORK AS intended"
            ))
            dao.insertNote(Memo( noteText = "FIRST NOTE INSHALL THIS WILL WORK AS intended"))
            dao.insertNote(Memo( noteText = "FIRST NOTE INSHALL THIS WILL WORK AS intended"))
            dao.insertTask(ToDoTask(description = "DO THE DISHES" , important = true , done = true))
            dao.insertTask(ToDoTask(description = "DO THE DISHES" , important = false , done = false))
            dao.insertTask(ToDoTask(description = "DO THE DISHES" , important = false , done = false))
            dao.insertTask(ToDoTask(description = "DO THE DISHES" , important = true , done = true))
            dao.insertTask(ToDoTask(description = "DO THE DISHES" , important = false , done = false))
            dao.insertTask(ToDoTask(description = "DO THE DISHES" , important = false , done = false))

        }
    }
}