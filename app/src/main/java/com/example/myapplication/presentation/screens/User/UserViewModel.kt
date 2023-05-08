package com.example.myapplication.presantation.screens.User

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.domain.usecases.card.DeleteAllCardsUseCase
import com.example.myapplication.domain.usecases.user.DeleteUserUseCase
import com.example.myapplication.domain.usecases.user.GetAllUserUseCase
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val deleteAllCardsUseCase: DeleteAllCardsUseCase
                                        ): ViewModel() {
    var isFinish by mutableStateOf(false)
    var login by mutableStateOf("")

    fun logOut(navHostController: NavHostController, authRegViewModel: AuthRegViewModel) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteUserUseCase.invoke()
            deleteAllCardsUseCase.invoke()
            authRegViewModel.isFinish = false
        }
//        navHostController.navigate(Screens.AuthScreen.rout)
    }
}