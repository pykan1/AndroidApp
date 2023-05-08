package com.example.myapplication.presantation.screens.AuthReg

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.api.RemoteDataSource
import com.example.myapplication.data.api.model.AuthRegClass
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.domain.usecases.AddCardUseCase
import com.example.myapplication.domain.usecases.CommitUserJsonUseCase
import com.example.myapplication.domain.usecases.GetAllCardsUseCase
import com.example.myapplication.domain.usecases.InsertUserUseCase

import com.example.myapplication.presantation.navigation.Screens
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthRegViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val insertUserUseCase: InsertUserUseCase,
    private val addCardUseCase: AddCardUseCase,
    private val commitUserJsonUseCase: CommitUserJsonUseCase
) : ViewModel() {
    var isLoading by mutableStateOf(false)
    val authReg = mapOf(true to "Войти", false to "Регистрация")
    var isFinish by mutableStateOf(false)
    var isAuth by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var auth = MutableStateFlow(AuthRegClass())

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun setAuthRegClass() {
        viewModelScope.launch{
            auth.emit(
                AuthRegClass()
            )
            errorMessage = ""
        }
    }

    fun isAuthChange() {
        viewModelScope.launch {
            isAuth = !isAuth
            setAuthRegClass()
        }
    }

    fun setLogin(item: String) {
        CoroutineScope(Dispatchers.IO).launch {
            auth.emit(
                AuthRegClass(
                    login = item,
                    hash_password = auth.value.hash_password
                )
            )
        }
    }

    fun setPassword(item: String) {
        CoroutineScope(Dispatchers.IO).launch {
            auth.emit(
                AuthRegClass(
                    login = auth.value.login,
                    hash_password = item
                )
            )
        }
    }

    fun register(authRegClass: AuthRegClass, navController: NavController) {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            try {
                handlerAuthReg()
                val data = remoteDataSource.getRegisterData(authRegClass)
                insertUserUseCase.invoke(data)
                Log.d("11", data.user_data)
                val userData = Gson().fromJson(data.user_data, Array<CardModel>::class.java)
                userData.forEach {
                    addCardUseCase.invoke(it) //долго прилетает, надо решать вопросики
                }
                isFinish = true
                setAuthRegClass()
            } catch (e: Exception) {
                errorMessageText(e.message.toString())
            }
        }
    }

    fun auth(authRegClass: AuthRegClass, navController: NavController) {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            try {
                handlerAuthReg()
                val data = remoteDataSource.getLoginData(authRegClass)
                insertUserUseCase.invoke(data)
                val userData = Gson().fromJson(data.user_data, Array<CardModel>::class.java)
                userData.forEach {
                    addCardUseCase.invoke(it)
                }
                isFinish = true
                setAuthRegClass()
            } catch (e: Exception) {
                errorMessageText(e.message.toString())
            }
        }
    }

    private fun errorMessageText(text: String) {
        errorMessage = when (text) {
            "HTTP 409 Conflict" -> "Такой пользователь уже существует"
            "HTTP 401 Unauthorized" -> "Аккаунт не найден"
            else -> text
        }
    }

    private fun handlerAuthReg() {
        errorMessage = when {
            auth.value.hash_password.length < 4 -> "Пароль должен содержать больше 4 символов"
            auth.value.hash_password == "12345" -> "Придумай пароль лучше :D"
            auth.value.login.length < 4 -> "Логин должен содержать больше 4 символов"
            else -> ""
        }
        if (errorMessage != "") {
            throw IllegalArgumentException(errorMessage)
        }
    }
}
