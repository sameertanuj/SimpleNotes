package com.myowndomain.myownnotes.app.utils

import androidx.recyclerview.widget.DiffUtil
import com.myowndomain.myownnotes.app.repository.Note

class NotesDiffUtil(private val oldNotes: List<Note>, private val newNotes: List<Note>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotes[oldItemPosition] == newNotes[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldNotes.size
    }

    override fun getNewListSize(): Int {
        return newNotes.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotes[oldItemPosition] == newNotes[newItemPosition]
    }

}