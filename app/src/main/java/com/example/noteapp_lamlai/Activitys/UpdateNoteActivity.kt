package com.example.noteapp_lamlai.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp_lamlai.Models.Note
import com.example.noteapp_lamlai.R
import com.example.noteapp_lamlai.ViewModels.NoteViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.activity_update_note.*
import kotlinx.android.synthetic.main.activity_update_note.edt_Note_Description
import kotlinx.android.synthetic.main.activity_update_note.edt_Note_Title
import kotlinx.android.synthetic.main.activity_update_note.txtDateNote
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class UpdateNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this,
            NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)
        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        edt_Note_Title.setText(note.title)
        edt_Note_Description.setText(note.description)
        //txtDateNote.setText(note.date)
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        txtDateNote.text =  currentDate.toString()

        btnUpdate_Note.setOnClickListener {

            if (edt_Note_Title.text.toString() ==""){
                showToast("Title not null")
                edt_Note_Title.requestFocus()
            }else if (edt_Note_Description.text.toString() ==""){
                showToast("Description not null")
                edt_Note_Description.requestFocus()
            }else{
                val note = Note(edt_Note_Title.text.toString(),edt_Note_Description.text.toString(),txtDateNote.text.toString())
                noteViewModel.updateNote(note)
                showToast("Update Note Successful")
                finish()
            }
        }
    }
    fun showToast(string: String){
        Toast.makeText(this.application,string, Toast.LENGTH_LONG).show()
    }
}