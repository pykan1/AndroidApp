package com.example.myapplication.presentation.navigation

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase
) : ViewModel() {
    val user = runBlocking { getAllUserUseCase.invoke() }.isEmpty()
}