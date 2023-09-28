package com.casecode.data.repository

import com.casecode.data.local.UserDao
import com.casecode.domain.entity.User
import com.casecode.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun addUser(user: User) {
        userDao.addUser(user)
    }

    override fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override fun readAllData(): Flow<List<User>> = userDao.readAllData()
}