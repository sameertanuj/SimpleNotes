package com.myowndomain.myownnotes.app.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.myowndomain.myownnotes.app.R
import com.myowndomain.myownnotes.app.repository.Note
import com.myowndomain.myownnotes.app.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.notes_fragment.*
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AddNotesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NotesViewModel
    private var noteID = 0
    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpToolbar()
        Log.i("AddNotesView", arguments.toString())
        if (arguments != null) {
           fillData()
        }
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(NotesViewModel::class.java)
    }

    private fun setUpToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(add_notes_toolbar)
        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun fillData() {
        note = arguments!!.getSerializable("Note") as Note
        noteID = note!!.id
        note_title.text.append(note!!.title)
        note_description.text.append(note!!.description)
    }

//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater?.inflate(R.menu.add_notes, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//
//        return when (item?.itemId) {
//            R.id.save -> {
//                handleSaveNotes(activity!!.findViewById(R.id.save))
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onDestroyView() {
        if (note_title.text.toString().isNotBlank() && note_description.text.toString().isNotBlank() && isEdited()) {
            Log.i("Saving", "Saving Note")
            viewModel.insert(Note(noteID, note_title.text.toString(), note_description.text.toString()))
        }
        super.onDestroyView()
    }

    private fun isEdited() : Boolean {
        return note_title.text.toString() != note?.title || note_description.text.toString() != note?.description
    }


}
