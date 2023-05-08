package com.example.myapplication.domain.usecases.user

import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke() = userRepository.clearUser()
}