package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.api.PostService
import com.example.myapplication.data.local.AppDatabase
import com.example.myapplication.data.local.AppDatabase2

import com.example.myapplication.data.local.repository.CardRepository
import com.example.myapplication.data.local.repository.UserRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CardDatabaseModule {

    @Provides
    fun provideAppDateCard(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "card"
        ).build()
    }

    @Provides
    fun providePostService(): PostService {
        return Retrofit.Builder()
            .baseUrl("http://62.113.106.162:8030")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }

    @Provides
    fun provideCardDao(appDatabase: AppDatabase): CardRepository {
        return appDatabase.cardDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object UserDatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): AppDatabase2 {
        return Room.databaseBuilder(
            appContext,
            AppDatabase2::class.java,
            "user"
        ).build()
    }

    @Provides
    fun provideUserRepository(appDatabase: AppDatabase2): UserRepository {
        return appDatabase.userDao()
    }

}
