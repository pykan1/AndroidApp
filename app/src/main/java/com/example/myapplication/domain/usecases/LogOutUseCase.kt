package com.example.myapplication.domain.usecases

import com.example.myapplication.domain.usecases.card.DeleteAllCardsUseCase
import com.example.myapplication.domain.usecases.user.DeleteUserUseCase
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