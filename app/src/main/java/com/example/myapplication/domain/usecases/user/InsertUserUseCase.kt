package com.example.myapplication.domain.usecases.user

import com.example.myapplication.domain.model.UserModel
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend fun invoke(userModel: UserModel) = userRepository.insertUser(userModel)
}