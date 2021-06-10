package com.sablab.memotodolist.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Memo( @PrimaryKey(autoGenerate = true) val id:Int = 0,
            @ColumnInfo(name = "note") val noteText:String

):Serializable {

}