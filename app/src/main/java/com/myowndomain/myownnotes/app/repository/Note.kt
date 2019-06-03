package com.myowndomain.myownnotes.app.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(@PrimaryKey(autoGenerate = true) val id: Int, val title: String, val description: String) : Serializable