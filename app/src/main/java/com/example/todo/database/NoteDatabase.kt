package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.dao.NotesDao
import com.example.todo.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {

abstract fun notesDao():NotesDao


companion object{

    @Volatile
    var INSTANCE :NoteDatabase ?=null

    fun getDataBaseInstance(context: Context) :NoteDatabase{

        val tempInstance= INSTANCE
        if (tempInstance !=null){
          return tempInstance
        }
        synchronized(this){
            val roomDatabaseInstance=Room.databaseBuilder(context,
                NoteDatabase::class.java,"note_db").allowMainThreadQueries().build()

            INSTANCE=roomDatabaseInstance
            return return  roomDatabaseInstance
        }
    }
}
}