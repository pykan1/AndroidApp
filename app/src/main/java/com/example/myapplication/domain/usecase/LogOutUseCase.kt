package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.usecase.card.DeleteAllCardsUseCase
import com.example.myapplication.domain.usecase.user.DeleteUserUseCase
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val deleteUserUseCase: DeleteUserUseCase,
    private val deleteAllCardsUseCase: DeleteAllCardsUseCase
) {
    suspend fun invoke() {
        deleteUserUseCase.invoke()
        deleteAllCardsUseCase.invoke()
    }
}