package com.example.robokalam.RoomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

// Portfolio.kt
@Entity
data class Portfolio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val college: String,
    val skills: String,
    val projectTitle: String,
    val projectDescription: String
)