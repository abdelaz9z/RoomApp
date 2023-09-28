package com.casecode.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.casecode.domain.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}