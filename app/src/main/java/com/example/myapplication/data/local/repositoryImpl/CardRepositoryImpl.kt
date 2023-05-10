package com.example.myapplication.data.local.repositoryImpl

import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardRepositoryImpl: CardRepository) {

    suspend fun getAllNotes(): List<CardModel> = cardRepositoryImpl.getAllCards()
    suspend fun findByTitle(subString: String): List<CardModel> = cardRepositoryImpl.searchByTitle(subString)
    suspend fun insertNote(cardModel: CardModel) = cardRepositoryImpl.insertCard(cardModel = cardModel)
    suspend fun deleteNote(cardModel: CardModel) = cardRepositoryImpl.deleteCard(cardModel = cardModel)
    suspend fun getNoteById(id: Long) = cardRepositoryImpl.getCardById(cardId = id)

}