package com.example.myapplication.domain.usecase.card

import com.example.myapplication.data.local.repository.CardRepository
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke() = cardRepository.getAllCards()
}