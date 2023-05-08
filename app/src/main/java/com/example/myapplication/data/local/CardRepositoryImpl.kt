package com.example.myapplication.data.local

import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardRepositoryImpl: CardRepository) {

    suspend fun getAllNotes(): List<CardModel> = cardRepositoryImpl.getAllCards()
    suspend fun insertNote(cardModel: CardModel) = cardRepositoryImpl.insertCard(cardModel = cardModel)
    suspend fun deleteNote(cardModel: CardModel) = cardRepositoryImpl.deleteCard(cardModel = cardModel)
    suspend fun getNoteById(id: Long) = cardRepositoryImpl.getCardById(cardId = id)

}