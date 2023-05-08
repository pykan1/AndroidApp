package com.example.myapplication.presantation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import com.example.myapplication.presantation.ui.theme.topbar

@Composable
fun SetupNavHostScreen(navController: NavHostController, authRegViewModel: AuthRegViewModel) {
    Scaffold(
//        topBar = {
//            TopAppBar(
//                modifier = Modifier.height(40.dp),
//                backgroundColor = topbar,
//                title = { Text(text = "") },
//                navigationIcon = run {
//                    {
//                        IconButton(onClick = { navController.navigateUp() }) {
//                            Icon(
//                                imageVector = Icons.Filled.ArrowBack,
//                                contentDescription = ""
//                            )
//                        }
//                    }
//                }
//            )
//        },
        bottomBar = {
            if (authRegViewModel.isFinish) {
                BottomBar(navController = navController)
            }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                SetupNavHost(navController = navController, authRegViewModel)
            }
        }
    )
}
