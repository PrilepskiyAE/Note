package com.ambrella.note.repository

import android.content.Context
import com.ambrella.note.model.DaoNote
import com.ambrella.note.model.Note
import com.ambrella.note.model.RoomDatabaseNote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(context: Context, private val backgroundDispatcher: CoroutineDispatcher):RepositoryNote {
   private val noteDao: DaoNote

   init {
        val database = RoomDatabaseNote.getInstance(context, backgroundDispatcher)
        noteDao = database!!.daoNote()
    }

    override fun getAllNote(): Flow<List<Note>> {
        return noteDao.getNote()
    }

    override suspend fun insert(note: Note) {
        withContext(backgroundDispatcher)
        {
            noteDao.insert(note)
        }
    }

    override suspend fun deleteAll() {
        withContext(backgroundDispatcher) {
            noteDao.deleteAll()
        }
    }

    override suspend fun delete(note: Note) {
        withContext(backgroundDispatcher) {
            noteDao.delete(note)
        }
    }

    override suspend fun update(note: Note) {
        withContext(backgroundDispatcher){
            noteDao.update(note)
        }
    }

}