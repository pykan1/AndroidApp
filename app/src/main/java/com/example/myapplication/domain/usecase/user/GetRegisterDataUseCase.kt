package com.example.myapplication.domain.usecase.user

import com.example.myapplication.data.api.RemoteDataSource
import com.example.myapplication.data.api.model.AuthRegClass
import javax.inject.Inject

class GetRegisterDataUseCase @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun invoke(authRegClass: AuthRegClass) = remoteDataSource.getRegisterData(authRegClass)
}