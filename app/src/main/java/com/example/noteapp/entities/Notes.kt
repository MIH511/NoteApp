package com.example.noteapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
class Notes(
        @PrimaryKey(autoGenerate = true)
        var id:Int?=null,

        @ColumnInfo(name = "title")
        var title:String?=null,

        @ColumnInfo(name = "sub title")
        var subTitle:String?=null,

        @ColumnInfo(name = "data time")
        var dateTime:String?=null,

        @ColumnInfo(name = "note text")
        var noteText:String?=null,

        @ColumnInfo(name = "image path")
        var imagePath:String?=null,

        @ColumnInfo(name = "web link")
        var webLink:String?=null,

        @ColumnInfo(name = "color")
        var color:String?=null,


):Serializable
{
    override fun toString(): String {
        return "$title : $dateTime"
    }
}
