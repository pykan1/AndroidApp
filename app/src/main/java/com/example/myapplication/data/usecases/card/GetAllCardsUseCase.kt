package com.example.myapplication.data.usecases.card

import com.example.myapplication.data.local.repository.CardRepository
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke() = cardRepository.getAllCards()
}