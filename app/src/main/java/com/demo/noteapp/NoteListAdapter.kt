package com.demo.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.noteapp.databinding.ItemNoteListBinding
import com.demo.noteapp.models.Notes
import javax.inject.Inject

class NoteListAdapter @Inject constructor() :
    ListAdapter<Notes, NoteListAdapter.NoteViewHolder>(DiffCallback()) {
    lateinit var notesClickListener: NoteClickListener

    class NoteViewHolder constructor(private val binding: ItemNoteListBinding,private val noteClickListener: NoteClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes) {
            binding.item = item
            binding.listener = noteClickListener
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemNoteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding,notesClickListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}