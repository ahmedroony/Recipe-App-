package com.example.recipeapp.database.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val fullName: String,
    val passwordHash: String
)