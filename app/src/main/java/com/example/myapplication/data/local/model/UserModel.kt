package com.example.myapplication.data.local.model

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.example.myapplication.R
import com.google.gson.Gson

@Entity(tableName = "user")
data class UserModel (
    @PrimaryKey
    val refresh_token: String = "",
    val access_token: String = "",
    val settings: String = Gson().toJson(SettingsModel()),
    var user_data: String = Gson().toJson(items))

val items = arrayOf(
    CardModel(
        imageId = R.drawable.math,
        title = "Теорема Виета",
        body = "Сумма корней равна второму коэффициенту с противоположным знаком, а произведение корней равняется свободному члену.",
        formula = "fskfsf",
        arrayhint = "Введите A, Введите B, Введите C"
    ),
    CardModel(
        imageId = R.drawable.math,
        title = "Нахождение объема",
        body = "Объём прямоугольного параллелепипеда равен произведению его длины, ширины и высоты. Переведите все в одни еденицы измерения",
        formula = "a*b*v",
        arrayhint = "Введите длинну, Введите ширину, Введите высоту"
    ),
    CardModel(
        imageId = R.drawable.physics,
        title = "Нахождение плотности",
        body = "Плотность — скалярная физическая величина, определяемая как отношение массы тела к занимаемому этим телом объёму или как производная массы по объёму",
        formula = "v:m",
        arrayhint = "Введите массу, Введите объем"
    )
)