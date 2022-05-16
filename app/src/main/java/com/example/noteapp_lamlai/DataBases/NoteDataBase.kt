package com.example.noteapp_lamlai.DataBases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp_lamlai.DataBases.Dao.NoteDao
import com.example.noteapp_lamlai.Models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase :RoomDatabase(){
    abstract fun getNoteDao(): NoteDao

    companion object{
        @Volatile
        private  var instance : NoteDataBase?=null

        fun getInstance(context: Context) : NoteDataBase{
            if (instance== null){
                instance = Room.databaseBuilder(context, NoteDataBase::class.java, "NoteDatabase").build()
            }
            return instance!!
        }
    }

}