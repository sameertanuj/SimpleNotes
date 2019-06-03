package com.myowndomain.myownnotes.app.ui

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.myowndomain.myownnotes.app.R
import com.myowndomain.myownnotes.app.repository.Note
import com.myowndomain.myownnotes.app.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.notes_list_view_item.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.DiffUtil
import com.myowndomain.myownnotes.app.utils.NotesDiffUtil


class NotesAdapter(private val notesViewModel: NotesViewModel) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    var notes: List<Note> = emptyList()
    val selectedItems = ArrayList<Note>()
    var multiSelect = false
    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.description.text = notes[position].description
        holder.noteItem.setOnClickListener {
            if(multiSelect) {
                selectItem(it, notes[position])
            }
            else {
                val bundle = Bundle()
                bundle.putSerializable("Note" , notes[position])
                Navigation.findNavController(it).navigate(R.id.action_notesViewFragment_to_notesFragment, bundle)
            }
        }
        setDeleteNotesOnLongClick(holder, position)
        updateNoteItemsBackgroundColor(holder, position)
    }

    private fun selectItem(noteItem : View, note: Note) {
        if(selectedItems.contains(note)) {
            selectedItems.remove(note)
            noteItem.setBackgroundColor(Color.WHITE)
        } else {
            selectedItems.add(note)
            noteItem.setBackgroundColor(Color.LTGRAY)
        }

    }

    private fun setDeleteNotesOnLongClick(holder: NoteViewHolder, position: Int) {
        val actionModeCallbacks = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                multiSelect = true
                val menuInflater: MenuInflater = mode.menuInflater
                menuInflater.inflate(R.menu.view_notes, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                selectedItems.forEach {
                    notesViewModel.delete(it)
                }
                mode.finish()
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode) {
                multiSelect = false
                selectedItems.clear()
                notifyDataSetChanged()
            }
        }
        holder.noteItem.setOnLongClickListener {
            (it.context as AppCompatActivity).startSupportActionMode(actionModeCallbacks)
            selectItem(it, notes[position])
            true
        }
    }

    private fun updateNoteItemsBackgroundColor(holder: NoteViewHolder, position: Int) {
        if (selectedItems.contains(notes[position])) {
            holder.noteItem.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.noteItem.setBackgroundColor(Color.WHITE)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val notesListViewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_view_item, parent, false) as View
        return NoteViewHolder(notesListViewItem)
    }

    fun addNotes(notes : List<Note>) {
        val diffResult = DiffUtil.calculateDiff(NotesDiffUtil(this.notes, notes), true)
        this.notes = notes
        diffResult.dispatchUpdatesTo(this)
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.notes_title!!
        val description = view.notes_description!!
        val noteItem = view.note_item!!
    }




}