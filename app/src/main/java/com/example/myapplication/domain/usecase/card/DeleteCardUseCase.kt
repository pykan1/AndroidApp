package com.example.myapplication.domain.usecase.card

import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.repository.CardRepository
import com.example.myapplication.data.local.repositoryImpl.CardRepositoryImpl
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(private val cardRepositoryImpl: CardRepositoryImpl) {
    suspend fun invoke(cardModel: CardModel) = cardRepositoryImpl.deleteCard(cardModel = cardModel)
}