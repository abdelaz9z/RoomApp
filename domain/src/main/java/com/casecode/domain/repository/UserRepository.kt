package com.casecode.domain.repository

import com.casecode.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun addUser(user: User)

    fun updateUser(user: User)

    fun deleteUser(user: User)

    fun deleteAllUsers()

    fun readAllData(): Flow<List<User>>
}