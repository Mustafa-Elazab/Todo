package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.dao.NotesDao
import com.example.todo.data.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteRepository(val noteDao :NotesDao) {

    fun getAllNotes():LiveData<List<NoteModel>>{

        return noteDao.getNotes()
    }

    fun getHighNotes():LiveData<List<NoteModel>>{

        return noteDao.getHighNotes()
    }


    fun getMeduimNotes():LiveData<List<NoteModel>>{

        return noteDao.getMeduimNotes()
    }


    fun getLowNotes():LiveData<List<NoteModel>>{

        return noteDao.getLowNotes()
    }


    fun insertNote(note:NoteModel){
        return noteDao.insertNote(note)
    }

    fun deleteNote( id:Int){
        return noteDao.deleteNote(id)
    }

    fun updateNote(note: NoteModel){
       GlobalScope.launch(Dispatchers.IO) {
            noteDao.noteUpdate(note)
       }
    }
}