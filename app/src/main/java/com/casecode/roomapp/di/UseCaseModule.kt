package com.casecode.roomapp.di

import com.casecode.domain.repository.UserRepository
import com.casecode.domain.usecase.UserCRUD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUserCRUD(userRepository: UserRepository): UserCRUD {
        return UserCRUD(userRepository)
    }
}