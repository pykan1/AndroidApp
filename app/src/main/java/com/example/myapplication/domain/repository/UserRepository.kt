package com.example.myapplication.domain.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.model.UserModel
import javax.inject.Named

@Dao
interface UserRepository {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserModel>

    @Query("SELECT * FROM user WHERE access_token = :accessToken")
    suspend fun getUser(accessToken: String): List<UserModel>

    @Query("DELETE FROM user")
    suspend fun clearUser()

    @Query("UPDATE user SET user_data = :newJson WHERE access_token = :accessToken")
    suspend fun updateJson(accessToken: String, newJson: String)

    @Insert
    suspend fun insertUser(user: UserModel)
}