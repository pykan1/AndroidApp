package com.example.myapplication.domain.usecases

import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.domain.repository.CardRepository
import javax.inject.Inject

class AddCardUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke(cardModel: CardModel) = cardRepository.insertCard(cardModel = cardModel)
}