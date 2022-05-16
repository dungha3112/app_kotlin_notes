package com.example.noteapp_lamlai.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_lamlai.Models.Note
import com.example.noteapp_lamlai.R

class NoteAdapter(
    private val context: Context,
    private val onClick:(Note)->Unit,
    private val onDelete:(Note)->Unit

) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notes: List<Note> = listOf()

    inner class  NoteViewHolder(itemView :View): RecyclerView.ViewHolder(itemView){
        // anh xa
        private val txt_item_Date : TextView = itemView.findViewById(R.id.txt_item_date)
        private val txt_item_Title : TextView = itemView.findViewById(R.id.txt_item_title)
        private val txt_item_Description : TextView = itemView.findViewById(R.id.txt_item_description)
        private val btnDelete : ImageView = itemView.findViewById(R.id.btn_Delete_Note)
        private val layout_item : ConstraintLayout = itemView.findViewById(R.id.layout_item)
        fun onBind(note: Note){
            txt_item_Date.text = note.date
            txt_item_Title.text = note.title
            txt_item_Description.text = note.description
            btnDelete.setOnClickListener {
                 onDelete(note)
            }

            layout_item.setOnClickListener {
                onClick(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return  NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    override fun getItemCount(): Int {
        return  notes.size
    }
    fun  setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

}