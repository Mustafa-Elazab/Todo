package com.example.todo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note_db")
 class NoteModel(
    @PrimaryKey(autoGenerate = true)
     var id:Int?=null,
     var title:String,
     var description:String,
     var date:String,
     var proietry:String
 ): Parcelable