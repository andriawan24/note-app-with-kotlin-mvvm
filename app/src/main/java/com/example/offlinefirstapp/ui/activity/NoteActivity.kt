package com.example.offlinefirstapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.offlinefirstapp.R
import com.example.offlinefirstapp.databinding.ActivityNoteBinding
import com.example.offlinefirstapp.db.NoteDatabase
import com.example.offlinefirstapp.repository.NoteRepository
import com.example.offlinefirstapp.utils.shortToast
import com.example.offlinefirstapp.viewmodel.NoteActivityViewModel
import com.example.offlinefirstapp.viewmodel.NoteActivityViewModelFactory
import java.lang.Exception

class NoteActivity : AppCompatActivity() {

    lateinit var noteActivityViewModel: NoteActivityViewModel
    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        try {
            setContentView(binding.root)
            val noteRepository = NoteRepository(NoteDatabase(this))
            val noteViewModelProviderFactory = NoteActivityViewModelFactory(noteRepository)
            noteActivityViewModel = ViewModelProvider(this, noteViewModelProviderFactory) [
                NoteActivityViewModel::class.java
            ]
        } catch (e: Exception) {
            shortToast("Error Occurred: $e")
        }
    }
}