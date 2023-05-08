package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.domain.model.UserModel
import com.example.myapplication.domain.repository.CardRepository
import com.example.myapplication.domain.repository.UserRepository

import javax.inject.Named


@Database(entities = [CardModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cardDao(): CardRepository
}


@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase2: RoomDatabase() {
    abstract fun userDao(): UserRepository
}