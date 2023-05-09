package com.example.myapplication.data.usecases.card

import com.example.myapplication.data.local.repository.CardRepository
import javax.inject.Inject

class GetCardByIdUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke(id: Long) = cardRepository.getCardById(cardId = id)
}