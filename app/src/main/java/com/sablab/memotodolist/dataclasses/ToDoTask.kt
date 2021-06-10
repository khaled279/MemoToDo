package com.sablab.memotodolist.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class ToDoTask(@PrimaryKey(autoGenerate = true)  val id:Int= 0,
               @ColumnInfo(name = "task")  val description:String,
               @ColumnInfo(name = "important") val important:Boolean = false,
               val done:Boolean = false,
               ) {


}