package com.example.myapplication.data.usecases.user

import com.example.myapplication.data.api.RemoteDataSource
import com.example.myapplication.data.api.model.UserJsonClass
import javax.inject.Inject

class CommitUserJsonUseCase @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun invoke(userJsonClass: UserJsonClass) = remoteDataSource.commitUserJson(userJsonClass)
}