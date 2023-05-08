package com.example.myapplication.domain.usecases.user

import com.example.myapplication.data.api.RemoteDataSource
import com.example.myapplication.data.api.model.AuthRegClass
import javax.inject.Inject

class GetLoginDataUseCase @Inject constructor(private val remoteDataSource: RemoteDataSource){
    suspend fun invoke(authRegClass: AuthRegClass) = remoteDataSource.getLoginData(authRegClass)
}