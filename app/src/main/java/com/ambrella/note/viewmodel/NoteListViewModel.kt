package com.ambrella.note.viewmodel

import androidx.lifecycle.*
import com.ambrella.note.model.Note
import com.ambrella.note.repository.NoteRepositoryImpl
import kotlinx.coroutines.launch

class NoteListViewModel(private val repository:NoteRepositoryImpl) : ViewModel() {
    val data: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun insertNote(note: Note) {
        showProgress()
        viewModelScope.launch { repository.insert(note) }
        hideProgress()
    }

    fun updateNote(note: Note) {
        showProgress()
        viewModelScope.launch { repository.update(note) }
        hideProgress()
    }

    fun deleteNote(note: Note) {
        showProgress()
        viewModelScope.launch {
            repository.delete(note)
        }
        hideProgress()
    }

    fun deleteNoteAll(note: Note) {
        showProgress()
        viewModelScope.launch {
            repository.delete(note)
        }
        hideProgress()
    }

    fun getAllNote(): LiveData<List<Note>> {
        val notes: LiveData<List<Note>>?
        showProgress()
        notes = repository.getAllNote().asLiveData()
        hideProgress()
        return notes
    }

    private fun showProgress() {
        _dataLoading.value = true
    }

    private fun hideProgress() {
        _dataLoading.value = false
    }


}