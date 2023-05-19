package com.example.myapplication.presantation.screens.User

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.domain.usecase.LogOutUseCase
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import com.example.myapplication.presantation.navigation.Screens
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import com.example.myapplication.presentation.navigation.NavigationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val getAllUserUseCase: GetAllUserUseCase
                                        ): ViewModel() {
    var isFinish by mutableStateOf(false)
    var login by mutableStateOf("")

    fun logOut(navHostController: NavHostController, authRegViewModel: AuthRegViewModel) {
        CoroutineScope(Dispatchers.IO).launch {
            logOutUseCase.invoke()
            Log.d("11", getAllUserUseCase.invoke().size.toString())
        }
        authRegViewModel.isFinish = false
        navHostController.navigate(Screens.AuthScreen.rout) {
            popUpTo(0)
        }

    }
}