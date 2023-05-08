package com.example.myapplication.domain.usecases.card

import com.example.myapplication.domain.repository.CardRepository
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke() = cardRepository.getAllCards()
}