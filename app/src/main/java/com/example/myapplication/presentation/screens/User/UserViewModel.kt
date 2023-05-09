package com.example.myapplication.presantation.screens.User

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.data.usecases.LogOutUseCase
import com.example.myapplication.data.usecases.card.DeleteAllCardsUseCase
import com.example.myapplication.data.usecases.user.DeleteUserUseCase
import com.example.myapplication.data.usecases.user.GetAllUserUseCase
import com.example.myapplication.presantation.navigation.Screens
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase
                                        ): ViewModel() {
    var isFinish by mutableStateOf(false)
    var login by mutableStateOf("")

    fun logOut(navHostController: NavHostController, authRegViewModel: AuthRegViewModel) {
        CoroutineScope(Dispatchers.IO).launch {
            logOutUseCase.invoke()
        }
        authRegViewModel.isFinish = false
        navHostController.navigate(Screens.AuthScreen.rout) {
            popUpTo(0)
        }
    }
}