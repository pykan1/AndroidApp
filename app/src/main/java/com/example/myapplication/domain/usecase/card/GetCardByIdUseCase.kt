package com.example.myapplication.domain.usecase.card

import com.example.myapplication.data.local.repository.CardRepository
import com.example.myapplication.data.local.repositoryImpl.CardRepositoryImpl
import javax.inject.Inject

class GetCardByIdUseCase @Inject constructor(private val cardRepositoryImpl: CardRepositoryImpl) {
    suspend fun invoke(id: Long) = cardRepositoryImpl.getCardById(id = id)
}