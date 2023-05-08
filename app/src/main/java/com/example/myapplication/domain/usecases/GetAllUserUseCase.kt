package com.example.myapplication.domain.usecases

import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke() = userRepository.getAll()
}