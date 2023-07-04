package com.example.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.dao.NoteDao
import com.example.noteapp.entities.Notes


@Database(entities = [Notes::class],version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase() {

    companion object{
        var noteDatabase:NoteDatabase?=null

        fun getDatabase(context: Context):NoteDatabase{

            if (noteDatabase==null){
                noteDatabase=Room.databaseBuilder(context,NoteDatabase::class.java,"nte.db").build()
            }
            return noteDatabase!!
        }
    }

    abstract fun noteDao():NoteDao
}