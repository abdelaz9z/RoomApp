package com.casecode.domain.usecase

import com.casecode.domain.entity.User
import com.casecode.domain.repository.UserRepository

class UserCRUD(private val userRepository: UserRepository) {

    fun readAllData() = userRepository.readAllData()

    fun addUser(user: User) {
        userRepository.addUser(user)
    }

    fun updateUser(user: User) {
        userRepository.updateUser(user)
    }

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
    }

    fun deleteAllUsers() {
        userRepository.deleteAllUsers()
    }
}