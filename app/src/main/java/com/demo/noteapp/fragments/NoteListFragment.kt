package com.demo.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.noteapp.NoteClickListener
import com.demo.noteapp.NoteListAdapter
import com.demo.noteapp.NoteViewModel
import com.demo.noteapp.R
import com.demo.noteapp.databinding.FragmentNotesListBinding
import com.demo.noteapp.models.Notes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment:Fragment(), NoteClickListener {
    private lateinit var binding: FragmentNotesListBinding
    private val noteViewModel by activityViewModels<NoteViewModel>()
    @Inject
    lateinit var notesAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeData()
    }

    private fun initUI(){
        noteViewModel.getNotes()
        notesAdapter.notesClickListener = this
        binding.apply {
            rvNotes.apply {
                layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
                adapter = notesAdapter
            }
            addNoteFab.setOnClickListener{
                this@NoteListFragment.findNavController().navigate(R.id.openAddNoteFragment)
            }
        }
    }

    private fun observeData(){
        noteViewModel.notes.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                notesAdapter.submitList(it)
            }
        }
    }

    override fun noteClick(notes: Notes) {
        val bundle = Bundle().apply {
            putParcelable("NotesObj",notes)
        }
        findNavController().navigate(R.id.openAddNoteFragment,bundle)
    }
}