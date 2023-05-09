package com.example.myapplication.data.usecases

import com.example.myapplication.data.usecases.card.DeleteAllCardsUseCase
import com.example.myapplication.data.usecases.user.DeleteUserUseCase
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