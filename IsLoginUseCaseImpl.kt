package com.sarang.torang.di.dialogsbox_di

import com.sarang.torang.core.database.dao.LoggedInUserDao
import com.sarang.torang.dialogsbox.usecase.IsLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow

@InstallIn(SingletonComponent::class)
@Module
class IsLoginUseCaseImpl {
    @Provides
    fun providesIsLoginUseCase(
        loggedInUserDao: LoggedInUserDao
    ): IsLoginUseCase {
        return object : IsLoginUseCase {
            override fun invoke(): Flow<Boolean> {
                return loggedInUserDao.isLoginFlow()
            }
        }
    }
}