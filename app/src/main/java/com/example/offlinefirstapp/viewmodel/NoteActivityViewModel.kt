package com.example.offlinefirstapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinefirstapp.model.Note
import com.example.offlinefirstapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteActivityViewModel(private val repositoryObject: NoteRepository) : ViewModel() {

    fun saveNote(newNote: Note) = viewModelScope.launch(Dispatchers.IO) {
        repositoryObject.addNote(newNote)
    }

    private var imagePath: String? = null

    fun saveImagePath(path: String?) {
        imagePath = path
    }

    fun setImagePath(): String? {
        if (imagePath != null)
            return imagePath
        return null
    }

    fun updateNote(existingNote: Note) = viewModelScope.launch(Dispatchers.IO) {
        repositoryObject.updateNote(existingNote)
    }

    fun deleteNote(existingNote: Note) = viewModelScope.launch(Dispatchers.IO) {
        repositoryObject.deleteDao(existingNote)
    }

    fun searchNote(query: String): LiveData<List<Note>> {
        return repositoryObject.searchNote(query)
    }

    fun getAllNotes(): LiveData<List<Note>> = repositoryObject.getNote()

    override fun onCleared() {
        imagePath = null
        super.onCleared()
    }

}