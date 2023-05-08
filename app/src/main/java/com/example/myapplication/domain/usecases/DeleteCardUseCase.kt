package com.example.myapplication.domain.usecases

import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.domain.repository.CardRepository
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke(cardModel: CardModel) = cardRepository.deleteCard(cardModel = cardModel)
}