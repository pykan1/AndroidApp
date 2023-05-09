package com.example.myapplication.data.usecases.user

import com.example.myapplication.data.local.model.UserModel
import com.example.myapplication.data.local.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend fun invoke(userModel: UserModel) = userRepository.insertUser(userModel)
}