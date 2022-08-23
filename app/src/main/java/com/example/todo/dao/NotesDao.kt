package com.example.todo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo.data.model.NoteModel

@Dao
interface NotesDao  {

    @Query("SELECT * FROM note_db")
    fun getNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM note_db WHERE proietry=3")
    fun getHighNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM note_db WHERE proietry=2")
    fun getMeduimNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM note_db WHERE proietry=1")
    fun getLowNotes(): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote( note: NoteModel)

    @Query("DELETE FROM note_db WHERE id=:id")
    fun deleteNote(id:Int)

    @Update
    fun noteUpdate(note:NoteModel)

}