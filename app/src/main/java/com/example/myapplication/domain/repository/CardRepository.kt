package com.example.myapplication.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.model.CardModel
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

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