package com.example.myapplication.data.local

import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.domain.model.UserModel
import com.example.myapplication.domain.repository.CardRepository
import com.example.myapplication.domain.repository.UserRepository

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRepositoryImpl: UserRepository) {

    suspend fun getAll(): List<UserModel> = userRepositoryImpl.getAll()
    suspend fun insertUser(userModel: UserModel) = userRepositoryImpl.insertUser(userModel)


}