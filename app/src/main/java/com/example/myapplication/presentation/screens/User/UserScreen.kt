package com.example.myapplication.presantation.screens.User

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import com.example.myapplication.presantation.ui.theme.Background_main
import com.example.myapplication.presantation.ui.theme.Orange

@Composable
fun UserScreen(navController: NavHostController, authRegViewModel: AuthRegViewModel){
    val viewModel = hiltViewModel<UserViewModel>()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background_main)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(modifier = Modifier
                .background(Orange)
                .padding(5.dp)
                .fillMaxWidth()
                .height(25.dp)
                .clickable {
                    viewModel.logOut(navController, authRegViewModel)
                },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Выйти")
            }
        }
    }
}