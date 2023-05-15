package com.example.myapplication.domain.usecase.user

import com.example.myapplication.data.local.repositoryImpl.UserRepositoryImpl
import javax.inject.Inject

class CommitUserSettingsUseCase @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) {
    suspend fun invoke(newSettings: String, refreshToken: String) = userRepositoryImpl.updateSettings(newSettings, refreshToken)
}