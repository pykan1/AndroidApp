package com.example.myapplication.domain.usecases

import com.example.myapplication.domain.repository.CardRepository
import javax.inject.Inject

class DeleteAllCardsUseCase @Inject constructor(private val cardRepository: CardRepository) {
    suspend fun invoke() = cardRepository.deleteAll()
}