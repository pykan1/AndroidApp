package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.model.UserModel
import com.example.myapplication.data.local.repository.CardRepository
import com.example.myapplication.data.local.repository.UserRepository


@Database(entities = [CardModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cardDao(): CardRepository
}


@Database(entities = [UserModel::class], version = 2)
abstract class AppDatabase2: RoomDatabase() {
    abstract fun userDao(): UserRepository
}