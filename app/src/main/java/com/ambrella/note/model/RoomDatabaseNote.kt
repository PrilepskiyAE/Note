package com.ambrella.note.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineDispatcher

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class RoomDatabaseNote : RoomDatabase() {
    abstract fun daoNote(): DaoNote

    companion object {
        private var INSTANCE: RoomDatabaseNote? = null
        fun getInstance(
            context: Context,
            backgroundDispatcher: CoroutineDispatcher
        ): RoomDatabaseNote? {
            if (INSTANCE == null) {
                synchronized(RoomDatabaseNote::class)
                {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RoomDatabaseNote::class.java, "best_note_database"
                    ).build()
                }
            }
            return INSTANCE
        }


    }
}