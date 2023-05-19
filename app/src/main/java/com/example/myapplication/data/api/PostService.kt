package com.example.myapplication.data.api

import com.example.myapplication.data.api.model.AuthRegClass
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.model.SettingsModel
import com.example.myapplication.data.local.model.UserModel
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST("login")
    suspend fun getLoginData(@Body request: AuthRegClass): UserModel

    @POST("register")
    suspend fun getRegisterData(@Body request: AuthRegClass): UserModel

    @POST("commitJson")
    suspend fun commitUserJson(@Body request: UserJsonClass): Boolean

    @POST("getJson")
    suspend fun getUserJson(@Body request: UserJsonClass): Array<CardModel>

    @POST("updateSettings")
    suspend fun updateSettings(@Body request: UserJsonClass): Boolean
}