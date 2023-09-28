package com.casecode.roomapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.casecode.domain.entity.User
import com.casecode.domain.usecase.UserCRUD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userCRUD: UserCRUD) : ViewModel() {


    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, throwable.message.toString())
    }

    val readAllData = userCRUD.readAllData().asLiveData()

    fun addUser(user: User) = viewModelScope.launch(context = Dispatchers.IO + errorHandler) {
        userCRUD.addUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userCRUD.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userCRUD.deleteUser(user)
    }

    fun deleteAllUsers() = viewModelScope.launch(Dispatchers.IO) {
        userCRUD.deleteAllUsers()
    }

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }
}