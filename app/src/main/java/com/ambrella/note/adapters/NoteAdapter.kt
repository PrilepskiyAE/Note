package com.ambrella.note.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.note.R
import com.ambrella.note.model.Note

class NoteAdapter: RecyclerView.Adapter<noteViewHolder>() {

    private var notes:List<Note> = listOf()
    var onNoteClicLisener: OnNoteClicLisener? = null

    interface OnNoteClicLisener {
        fun onNoteClick(note: Note)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent,false)
        return noteViewHolder(view)
    }

    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        val quote = notes.get(position)
        holder.init(quote)
        holder.itemView.setOnClickListener {
            onNoteClicLisener?.onNoteClick(quote)
        }
    }

    fun setNote(t: List<Note>) {
        this.notes = t
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return notes.isEmpty()
    }

    fun getCity(pos: Int): Note {
        return notes[pos]
    }

    override fun getItemCount(): Int {
       return notes.size
    }
}

class noteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var mTitle: TextView? = null
    private var mText: TextView? =null

    init {

        mTitle = itemView.findViewById(R.id.item_note_name)
        mText=itemView.findViewById(R.id.item_note_text)
    }

    fun init(currentNote: Note) {
        mTitle?.text = currentNote.title
        mText?.text=currentNote.text
    }
}