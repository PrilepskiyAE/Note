package com.ambrella.note.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoNote {
    @Query("SELECT * FROM tablenote")
    fun getNote(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM tablenote")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note): Int

    @Update
    suspend fun update(note: Note)


}