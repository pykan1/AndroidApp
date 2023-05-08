package com.example.myapplication.presantation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.presantation.screens.AuthReg.AuthRegScreen
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import com.example.myapplication.presantation.screens.User.UserScreen
import com.example.myapplication.presantation.screens.calculator.CalculatorScreen
import com.example.myapplication.presantation.screens.calculator2.Calculator2Screen
import com.example.myapplication.presantation.screens.editor.EditorScreen
import com.example.myapplication.presantation.screens.main.MainScreen

sealed class Screens(val rout: String, val icon: ImageVector) {
    object MainScreen: Screens(rout = "main_screen", Icons.Default.Menu)
    object EditorScreen: Screens(rout = "editor_screen", Icons.Default.Edit)
    object CalcScreen: Screens(rout = "calc_screen/{title}/{body}/{formula}/{arrayhint}", Icons.Default.Home)
    object Calc2Screen: Screens(rout = "calc2_screen", Icons.Default.Menu)
    object UserScreen: Screens(rout = "user_screen", Icons.Default.AccountBox)
    object AuthScreen: Screens(rout = "authreg_screen", Icons.Default.LocationOn)
    object SetupNav: Screens(rout = "setup_nav", Icons.Default.LocationOn)
}

@Composable
fun SetupNavHost(navController: NavHostController, authRegViewModel: AuthRegViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.rout
    ) {
         composable(route = Screens.MainScreen.rout) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.EditorScreen.rout) {
            EditorScreen(navController = navController)
        }
        composable(route = Screens.Calc2Screen.rout) {
            Calculator2Screen(navController = navController)
        }
        composable(route = Screens.UserScreen.rout) {
            UserScreen(navController = navController, authRegViewModel)
        }
        composable(route = Screens.CalcScreen.rout) {
            val title = it.arguments?.getString("title").toString()
            val body = it.arguments?.getString("body").toString()
            val formula = it.arguments?.getString("formula").toString()
            val array = it.arguments?.getString("arrayhint").toString()
            if (title == "{title}") {
                Calculator2Screen(navController = navController)
            } else {
                CalculatorScreen(
                    card = CardModel(
                        title = title,
                        body = body,
                        formula = formula,
                        arrayhint = array
                    )
                )
            }
        }
    }
}