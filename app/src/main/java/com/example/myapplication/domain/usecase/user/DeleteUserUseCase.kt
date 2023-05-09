package com.example.myapplication.domain.usecase.user

import com.example.myapplication.data.local.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke() = userRepository.clearUser()
}