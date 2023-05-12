package com.example.myapplication.data.local.repositoryImpl

import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardRepositoryImpl: CardRepository) {

    suspend fun getAllCards(): List<CardModel> = cardRepositoryImpl.getAllCards()
    suspend fun findByTitle(subString: String): List<CardModel> = cardRepositoryImpl.searchByTitle(subString)
    suspend fun insertCard(cardModel: CardModel) = cardRepositoryImpl.insertCard(cardModel = cardModel)
    suspend fun deleteAll() = cardRepositoryImpl.deleteAll()
    suspend fun deleteCard(cardModel: CardModel) = cardRepositoryImpl.deleteCard(cardModel = cardModel)
    suspend fun getCardById(id: Long) = cardRepositoryImpl.getCardById(cardId = id)

}