package com.example.recipeapp.database.repository

import com.example.recipeapp.database.local.UserDao
import com.example.recipeapp.database.local.UserEntity

class UserRepository private constructor(
    private val userDao: UserDao
) {

    suspend fun register(user: UserEntity) {
        userDao.registerUser(user)
    }

    suspend fun login(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }

    companion object {
        @Volatile private var INSTANCE: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(userDao).also { INSTANCE = it }
            }
    }
}