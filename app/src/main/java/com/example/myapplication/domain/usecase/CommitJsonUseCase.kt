package com.example.myapplication.domain.usecase

import com.example.myapplication.data.api.RemoteDataSource
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.domain.usecase.card.GetAllCardsUseCase
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

class CommitJsonUseCase @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getAllUserUseCase: GetAllUserUseCase,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun invoke() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = getAllCardsUseCase.invoke()
            val user = getAllUserUseCase.invoke()
            remoteDataSource.commitUserJson(
                UserJsonClass(
                    userJson = Gson().toJson(data.toTypedArray()),
                    refresh_token = user[user.size - 1].refresh_token
                )
            )
        }
    }
}