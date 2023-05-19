package com.example.myapplication.domain.usecase.user

import com.example.myapplication.data.api.RemoteDataSource
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.data.local.model.SettingsModel
import javax.inject.Inject

class UpdateSettingsUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun invoke(userJsonClass: UserJsonClass) = remoteDataSource.updateSettings(userJsonClass = userJsonClass)
}