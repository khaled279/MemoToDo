package com.sablab.memotodolist.notesfrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sablab.memotodolist.R
import com.sablab.memotodolist.dataclasses.Memo

class NotesAdapter: ListAdapter<Memo,NotesAdapter.NotesViewHolder>(NotesCompartor()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.create(parent)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote)
    }


    class NotesViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
            private val textNote:TextView = itemView.findViewById(R.id.textView)
            fun bind(note:Memo){
                textNote.text = note.noteText
            }
        companion object {
            fun create(parent: ViewGroup): NotesViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.todo_list_item,  parent, false)
                return NotesViewHolder(view)
            }
        }
    }

    class NotesCompartor:DiffUtil.ItemCallback<Memo>(){
        override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem === newItem

        }

        override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem.noteText == newItem.noteText
        }

    }


}