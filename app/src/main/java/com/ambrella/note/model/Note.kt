package com.ambrella.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablenote")
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    @ColumnInfo val title:String="",
    @ColumnInfo val text:String=""
)
