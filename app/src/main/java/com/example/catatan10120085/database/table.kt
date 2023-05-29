package com.example.catatan10120085.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
//10120085 - Dion Cahyan IF-2
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var date: String = "",
    var judul: String = "",
    var kategori: String = "",
    var note: String = "",
)
