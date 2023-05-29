package com.example.catatan10120085.database

import androidx.room.*
//10120085 - Dion Cahyan IF-2
@Dao
interface DataDao {
    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNote(): List<Note>

    @Query("SELECT * FROM note WHERE id =:id")
    fun getNoteById(id: Int): Note
}