package com.example.myapplication.data.api

import com.example.myapplication.data.api.model.AuthRegClass
import com.example.myapplication.data.api.model.UserJsonClass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postService: PostService) {

    suspend fun getLoginData(authRegClass: AuthRegClass) = postService.getLoginData(authRegClass)

    suspend fun getRegisterData(authRegClass: AuthRegClass) = postService.getRegisterData(authRegClass)

    suspend fun commitUserJson(userJsonClass: UserJsonClass) = postService.commitUserJson(userJsonClass)

    suspend fun getUserJson(userJsonClass: UserJsonClass) = postService.getUserJson(userJsonClass)
}