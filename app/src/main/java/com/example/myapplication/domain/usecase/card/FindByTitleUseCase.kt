package com.example.myapplication.domain.usecase.card

import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.repositoryImpl.CardRepositoryImpl
import javax.inject.Inject

class FindByTitleUseCase @Inject constructor(private val cardRepositoryImpl: CardRepositoryImpl) {
    suspend fun invoke(substring: String): List<CardModel> = cardRepositoryImpl.findByTitle(substring)
}