package com.example.myapplication.data.api

import com.example.myapplication.data.api.model.AuthRegClass
import com.example.myapplication.data.api.model.UserClass
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.domain.model.UserModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
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
}