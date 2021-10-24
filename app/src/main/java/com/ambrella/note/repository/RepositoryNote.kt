package com.ambrella.note.repository

import com.ambrella.note.model.Note
import kotlinx.coroutines.flow.Flow

interface RepositoryNote {
    fun getAllNote(): Flow<List<Note>>
    suspend fun insert(note: Note)
    suspend fun deleteAll()
    suspend fun delete(note: Note)
    suspend fun update(note: Note)

}