package com.example.noteapp_lamlai.DataBases.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.noteapp_lamlai.DataBases.Dao.NoteDao
import com.example.noteapp_lamlai.DataBases.NoteDataBase
import com.example.noteapp_lamlai.Models.Note

class NoteRepository(app: Application) {
    private val noteDao:NoteDao

    init {
        val noteDataBase: NoteDataBase = NoteDataBase.getInstance(app)
        noteDao= noteDataBase.getNoteDao()
    }
    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun  getAllNote(): LiveData<List<Note>> = noteDao.getAllNote()


}