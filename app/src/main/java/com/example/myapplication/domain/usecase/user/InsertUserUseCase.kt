package com.example.myapplication.domain.usecase.user

import com.example.myapplication.data.local.model.UserModel
import com.example.myapplication.data.local.repository.UserRepository
import com.example.myapplication.data.local.repositoryImpl.UserRepositoryImpl
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepositoryImpl: UserRepositoryImpl){
    suspend fun invoke(userModel: UserModel) = userRepositoryImpl.insertUser(userModel)
}