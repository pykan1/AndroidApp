package com.example.myapplication.presantation.navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import com.example.myapplication.presantation.screens.AuthReg.AuthRegScreen
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import com.example.myapplication.presantation.screens.User.UserScreen
import com.example.myapplication.presantation.screens.calculator.CalculatorScreen
import com.example.myapplication.presantation.screens.calculator2.Calculator2Screen
import com.example.myapplication.presantation.screens.editor.EditorScreen
import com.example.myapplication.presantation.screens.main.MainScreen
import com.example.myapplication.presentation.navigation.NavigationViewModel

lateinit var getAllUserUseCase: GetAllUserUseCase

sealed class Screens(val rout: String, val icon: ImageVector) {
    object MainScreen : Screens(rout = "main_screen", Icons.Default.Menu)
    object EditorScreen : Screens(rout = "editor_screen", Icons.Default.Edit)
    object CalcScreen :
        Screens(rout = "calc_screen/{title}/{body}/{formula}/{arrayhint}", Icons.Default.Home)

    object Calc2Screen : Screens(rout = "calc2_screen", Icons.Default.Menu)
    object UserScreen : Screens(rout = "user_screen", Icons.Default.AccountBox)
    object AuthScreen : Screens(rout = "authreg_screen", Icons.Default.LocationOn)
}

@Composable
fun SetupNavHost (navController: NavHostController, authRegViewModel: AuthRegViewModel) {
    val viewModel = hiltViewModel<NavigationViewModel>()
    viewModel.initSettings()
    NavHost(
        navController = navController,
        startDestination = if (viewModel.user.isEmpty()) Screens.AuthScreen.rout else Screens.MainScreen.rout
    ) {
        composable(route = Screens.AuthScreen.rout) {
            Log.d("11", "AuthScreen")
            AuthRegScreen(navController = navController, authRegViewModel)
        }
        composable(route = Screens.MainScreen.rout) {
            Log.d("11", "MainScreen")
            MainScreen(navController = navController)
            authRegViewModel.isFinish = true
        }
        composable(route = Screens.EditorScreen.rout) {
            EditorScreen(navController = navController)
        }
        composable(route = Screens.Calc2Screen.rout) {
            Calculator2Screen(navController = navController, viewModel)
        }
        composable(route = Screens.UserScreen.rout) {
            UserScreen(navController = navController, authRegViewModel = authRegViewModel, navigationViewModel = viewModel)
        }
        composable(route = Screens.CalcScreen.rout) {
            val title = it.arguments?.getString("title").toString()
            val body = it.arguments?.getString("body").toString()
            val formula = it.arguments?.getString("formula").toString()
            val array = it.arguments?.getString("arrayhint").toString()
            if (title == "{title}") {
                Calculator2Screen(navController = navController, viewModel)
            } else {
                CalculatorScreen(
                    card = CardModel(
                        title = title,
                        body = body,
                        formula = formula,
                        arrayhint = array
                    ),
                    viewModel
                )
            }
        }
    }
}