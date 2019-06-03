package com.myowndomain.myownnotes.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.myowndomain.myownnotes.app.R
import com.myowndomain.myownnotes.app.repository.Note
import com.myowndomain.myownnotes.app.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_notes_view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class NotesViewFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var notesViewAdapter: NotesAdapter
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(notes_view_toolbar)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(NotesViewModel::class.java)
        notesViewAdapter = NotesAdapter(viewModel)
        notes_view.adapter = notesViewAdapter
            notes_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel.getAllNotes().observe(this,
            Observer<List<Note>> {
                if(it.isEmpty()) {
                    no_notes_view.visibility = View.VISIBLE
                    notes_view.visibility = View.GONE
                }
                else {
                    no_notes_view.visibility = View.GONE
                    notes_view.visibility = View.VISIBLE
                    notesViewAdapter.addNotes(it)
                }
            })
        add_notes.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.action_notesViewFragment_to_notesFragment)
        }
    }

}
