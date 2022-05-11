package com.ubaya.todoapp_160419060.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "notes")
    var notes: String,
    @ColumnInfo(name = "priority")
    var priority:Int,
    @ColumnInfo(name = "is_done")
    var is_done:Int //maybe because in MySQL don't have boolean data type
    // and instead convert boolean values into integers (1=TRUE, 0 = FALSE)
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}