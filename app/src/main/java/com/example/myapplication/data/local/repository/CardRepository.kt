package com.example.myapplication.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.local.model.CardModel

@Dao
interface CardRepository {

    @Insert
    suspend fun insertCard(cardModel: CardModel)

    @Query("DELETE FROM card")
    suspend fun deleteAll()

    @Query("SELECT * FROM card")
    suspend fun getAllCards(): List<CardModel>

    @Delete
    suspend fun deleteCard(cardModel: CardModel)

    @Query("SELECT * FROM card WHERE id=:cardId")
    suspend fun getCardById(cardId: Long): CardModel
}