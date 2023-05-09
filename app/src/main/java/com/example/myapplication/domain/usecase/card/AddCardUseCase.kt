package com.example.myapplication.domain.usecase.card

import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.repository.CardRepository
import javax.inject.Inject

class AddCardUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke(cardModel: CardModel) = cardRepository.insertCard(cardModel = cardModel)
}