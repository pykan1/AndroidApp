package com.example.myapplication.data.local.repositoryImpl

import com.example.myapplication.data.local.model.UserModel
import com.example.myapplication.data.local.repository.UserRepository

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRepositoryImpl: UserRepository) {

    suspend fun getAll(): List<UserModel> = userRepositoryImpl.getAll()
    suspend fun insertUser(userModel: UserModel) = userRepositoryImpl.insertUser(userModel)


}