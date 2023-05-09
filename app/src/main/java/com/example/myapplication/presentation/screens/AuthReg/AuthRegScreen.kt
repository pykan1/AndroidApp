package com.example.myapplication.presantation.screens.AuthReg

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.data.api.model.AuthRegClass
import com.example.myapplication.presantation.navigation.Screens
import com.example.myapplication.presantation.navigation.SetupNavHostScreen
import com.example.myapplication.presantation.ui.theme.*
import javax.inject.Inject

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthRegScreen(navController: NavHostController, viewModel: AuthRegViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val stateAuth by viewModel.auth.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Backgound_auth)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 10.dp, end = 10.dp, top = 124.dp),
                shape = RoundedCornerShape(30.dp),
                backgroundColor = Form_auth

            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    if (viewModel.isAuth) {
                        Auth(viewModel)
                    } else {
                        Register(viewModel)
                    }
                    if (viewModel.isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        keyboardController?.hide()
                        if (viewModel.isAuth) {
                            viewModel.auth(stateAuth, navController)
                        } else {
                            viewModel.register(stateAuth, navController)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Button_auth,
                        contentColor = text_auth
                    ),
                    modifier = Modifier
                        .padding(start = 60.dp, end = 60.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Text(
                        text = viewModel.authReg[viewModel.isAuth].toString(),
                        fontSize = 24.sp
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.clickable { viewModel.isAuthChange() },
                    textAlign = TextAlign.Center,
                    text = if (viewModel.isAuth) "Нет аккаунта?" else "Уже есть аккаунт?",
                    color = text_auth,
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = viewModel.errorMessage,
                    color = Color.Red,
                )
            }
        }
    }
}


@Composable
fun Auth(viewModel: AuthRegViewModel) {
    val stateAuth by viewModel.auth.collectAsState()
    Login(viewModel = viewModel, stateAuth = stateAuth)
    Password(ViewModel = viewModel, stateAuth = stateAuth)
}

@Composable
fun Register(viewModel: AuthRegViewModel) {
    val stateAuth by viewModel.auth.collectAsState()
    Login(viewModel = viewModel, stateAuth)
    Password(ViewModel = viewModel, stateAuth)
}

@Composable
fun Login(viewModel: AuthRegViewModel, stateAuth: AuthRegClass) {
    Box(modifier = Modifier) {
        Card(modifier = Modifier, shape = RoundedCornerShape(30.dp)) {
            TextField(
                value = stateAuth.login, {
                    viewModel.setLogin(it)
//                    viewModel2.exeception(it)
                },
                label = { Text(text = "Логин") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = text_auth,
                    backgroundColor = Plain_form_text
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                singleLine = true,
//                trailingIcon = {
//                    if (viewModel2.isError)
//                        Icon(Icons.Filled.Error, "error", tint = Color.Red)
//                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun Password(ViewModel: AuthRegViewModel, stateAuth: AuthRegClass) {
    Box(modifier = Modifier) {
        Card(modifier = Modifier, shape = RoundedCornerShape(30.dp)) {
            TextField(
                value = stateAuth.hash_password,
                {
                    ViewModel.setPassword(it)
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = text_auth,
                    backgroundColor = Plain_form_text
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                label = { Text(text = "Пароль") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}