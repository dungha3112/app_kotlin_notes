package com.example.noteapp_lamlai.DataBases.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp_lamlai.Models.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note : Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from note_table")
    fun getAllNote(): LiveData<List<Note>>
}