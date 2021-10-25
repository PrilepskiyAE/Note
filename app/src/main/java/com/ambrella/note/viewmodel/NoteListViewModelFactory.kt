package com.ambrella.note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ambrella.note.repository.NoteRepositoryImpl
import com.ambrella.note.repository.RepositoryNote

class NoteListViewModelFactory(private val repository: RepositoryNote) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteListViewModel(repository as NoteRepositoryImpl) as T
    }
}