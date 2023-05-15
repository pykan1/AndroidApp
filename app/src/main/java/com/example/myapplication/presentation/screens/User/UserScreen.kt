package com.example.myapplication.presantation.screens.User

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.presantation.screens.AuthReg.AuthRegViewModel
import com.example.myapplication.presantation.ui.theme.Background_main
import com.example.myapplication.presantation.ui.theme.EditorInput
import com.example.myapplication.presantation.ui.theme.Orange
import com.example.myapplication.presentation.navigation.NavigationViewModel

@Composable
fun UserScreen(
    navController: NavHostController,
    authRegViewModel: AuthRegViewModel,
    navigationViewModel: NavigationViewModel
) {
    val viewModel = hiltViewModel<UserViewModel>()
    val stateSettings by navigationViewModel.settings.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background_main)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(EditorInput)
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    )
                    {
                        Text(
                            text = "Сменить тип представления чисел",
                            fontSize = 17.sp,
                        )
                        Text(
                            text = """Режим "Большие числа" """,
                            fontSize = 15.sp
                        )
                    }
                    Switch(
                        checked = stateSettings.bigDecimalMode,
                        onCheckedChange = { navigationViewModel.bigDecimalModeChange() }
                    )
                }
            }
            Box(
                modifier = Modifier
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