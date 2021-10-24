package com.ambrella.note.viewmodel

import androidx.lifecycle.*
import com.ambrella.note.model.Note
import com.ambrella.note.repository.NoteRepositoryImpl
import kotlinx.coroutines.launch

class NoteListViewModel(private val repository:NoteRepositoryImpl) : ViewModel() {
    val data: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun insertCity(note: Note) {
        showProgress()
        viewModelScope.launch { repository.insert(note) }
        hideProgress()
    }

    fun updateCity(note: Note) {
        showProgress()
        viewModelScope.launch { repository.update(note) }
        hideProgress()
    }

    fun deleteCity(note: Note) {
        showProgress()
        viewModelScope.launch {
            repository.delete(note)
        }
        hideProgress()
    }

    fun deleteCityAll(note: Note) {
        showProgress()
        viewModelScope.launch {
            repository.delete(note)
        }
        hideProgress()
    }

    fun getAllCity(): LiveData<List<Note>> {
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