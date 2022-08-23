package com.example.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todo.data.model.NoteModel
import com.example.todo.database.NoteDatabase
import com.example.todo.repository.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    var noteRepository: NoteRepository

    init {
        var dao = NoteDatabase.getDataBaseInstance(application).notesDao()
        noteRepository = NoteRepository(dao)
    }

    fun addNote(noteModel: NoteModel) {
        return noteRepository.insertNote(noteModel)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return noteRepository.getAllNotes()
    }
    fun getHighNotes():LiveData<List<NoteModel>>{

        return noteRepository.getHighNotes()
    }


    fun getMeduimNotes():LiveData<List<NoteModel>>{

        return noteRepository.getMeduimNotes()
    }


    fun getLowNotes():LiveData<List<NoteModel>>{

        return noteRepository.getLowNotes()
    }
    fun deleteNote(id: Int) {
        return noteRepository.deleteNote(id)
    }

    fun updateNote(noteModel: NoteModel) {
        return noteRepository.updateNote(noteModel)
    }
}