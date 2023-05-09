package com.example.myapplication.data.usecases.card

import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.repository.CardRepository
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke(cardModel: CardModel) = cardRepository.deleteCard(cardModel = cardModel)
}