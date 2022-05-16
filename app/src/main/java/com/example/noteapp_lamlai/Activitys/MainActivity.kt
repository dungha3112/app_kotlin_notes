package com.example.noteapp_lamlai.Activitys


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp_lamlai.Adapters.NoteAdapter
import com.example.noteapp_lamlai.Models.Note
import com.example.noteapp_lamlai.R
import com.example.noteapp_lamlai.ViewModels.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val noteViewModel:NoteViewModel by lazy {
        ViewModelProvider(this,NoteViewModel.NoteViewModelFactory(this.application))[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initControls()
        initEvent()
    }

    private fun initEvent() {
        btn_Open_Add_Note.setOnClickListener {
            val intent= Intent(this, AddNoteActivity::class.java)
            startActivity(intent)

        }
    }

    private fun initControls() {
        val adapter : NoteAdapter = NoteAdapter(this@MainActivity,onItemClick,onItemDelete)
        rv_Note.setHasFixedSize(true)
        rv_Note.layoutManager = LinearLayoutManager(this)
        rv_Note.adapter = adapter

        noteViewModel.getAllNote().observe(this, Observer {
            adapter.setNotes(it)
        })

    }
    private val onItemClick:(note: Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE",it)
        startActivity(intent)
    }
    private val onItemDelete:(note: Note) -> Unit = {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Notification")
        //set message for alert dialog
        builder.setMessage("You agree to delete it ??")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            noteViewModel.deleteNote(it)
        }
        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}