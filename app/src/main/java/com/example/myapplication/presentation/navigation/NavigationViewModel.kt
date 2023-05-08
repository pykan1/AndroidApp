package com.example.myapplication.presentation.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapplication.domain.model.UserModel
import com.example.myapplication.domain.usecases.user.GetAllUserUseCase
import com.example.myapplication.presantation.navigation.Screens
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase
) : ViewModel() {
    val user = runBlocking { getAllUserUseCase.invoke() }.isEmpty()
}