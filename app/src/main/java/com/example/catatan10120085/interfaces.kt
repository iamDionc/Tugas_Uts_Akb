package com.example.catatan10120085

import com.example.catatan10120085.database.Note
//10120085 - Dion Cahyan IF-2
interface OnClickItemListener{
    fun onEditNote(note: Note)
    fun onDeleteNote(note: Note)
    fun onUpdateNote()
}