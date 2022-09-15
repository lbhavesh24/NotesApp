package com.demo.noteapp.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.noteapp.MainActivity
import com.demo.noteapp.NoteViewModel
import com.demo.noteapp.databinding.FragmentAddNoteBinding
import com.demo.noteapp.databinding.FragmentNotesListBinding
import com.demo.noteapp.models.Notes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment:Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private val noteViewModel by activityViewModels<NoteViewModel>()
    private var notes:Notes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            notes = getParcelable("NotesObj")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater,container,false)
        notes?.let {binding.item = it}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
        binding.apply {
            tvNotesDescription.setOnFocusChangeListener { _, hasFocus ->
                tvDone.isVisible = hasFocus
            }
            tvDone.setOnClickListener{
                val note = Notes(tvNotesDescription.text.toString())
                if (notes!=null){
                    notes!!.description = tvNotesDescription.text.toString()
                    noteViewModel.updateNote(notes!!)
                }else {
                    noteViewModel.insertNote(note)
                }
                requireActivity().onBackPressed()
            }
        }
    }
}