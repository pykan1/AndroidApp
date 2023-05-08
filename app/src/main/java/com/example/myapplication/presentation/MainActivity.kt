package com.example.myapplication.presantation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.domain.usecases.user.GetAllUserUseCase
import com.example.myapplication.presantation.navigation.*
import com.example.myapplication.presantation.screens.AuthReg.AuthRegScreen
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getAllUserUseCase: GetAllUserUseCase

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("11", "enter")
            val viewModel = hiltViewModel<AuthRegViewModel>()
            val navController = rememberNavController()
//            Log.d("11", user.toString())
            var hasUser by remember {
                mutableStateOf(runBlocking {
                    getAllUserUseCase.invoke()
                }.isNotEmpty())
            }
            Log.d("11", hasUser.toString())
            Log.d("11", viewModel.isFinish.toString())
            if (viewModel.isFinish || !viewModel.isFinish) {
                hasUser = runBlocking {
                    getAllUserUseCase.invoke()
                }.isNotEmpty()
            }
            if (!viewModel.isFinish && !hasUser) {
                AuthRegScreen(navController = navController, viewModel)
            } else {
                SetupNavHostScreen(navController = navController, viewModel)//fdsf
                viewModel.isFinish = true
            }
            window.insetsController?.apply {
                hide(WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}


