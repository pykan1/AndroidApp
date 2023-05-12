package com.example.myapplication.data.local.repositoryImpl

import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.model.UserModel
import com.example.myapplication.data.local.repository.UserRepository

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getAll(): List<UserModel> = userRepository.getAll()
    suspend fun insertUser(userModel: UserModel) = userRepository.insertUser(userModel)
    suspend fun getUser(accessToken: String): List<UserModel> = userRepository.getUser(accessToken)
    suspend fun updateJson(accessToken: String, newJson: String) = userRepository.updateJson(accessToken, newJson)
    suspend fun clearUser() = userRepository.clearUser()

}