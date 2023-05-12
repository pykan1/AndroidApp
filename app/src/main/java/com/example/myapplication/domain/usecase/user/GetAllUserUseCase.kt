package com.example.myapplication.domain.usecase.user

import com.example.myapplication.data.local.repository.UserRepository
import com.example.myapplication.data.local.repositoryImpl.UserRepositoryImpl
import javax.inject.Inject

class GetAllUserUseCase @Inject constructor(private val userRepositoryImpl: UserRepositoryImpl) {
    suspend fun invoke() = userRepositoryImpl.getAll()
}