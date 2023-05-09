package com.example.myapplication.presantation.screens.editor

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.myapplication.R
import com.example.myapplication.presantation.navigation.Screens
import com.example.myapplication.presantation.ui.components.AlertMessage
import com.example.myapplication.presantation.ui.theme.*
import com.example.myapplication.presentation.ui.components.InformationItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditorScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<AddViewModel>()
    val stateCard by viewModel.cardModel.collectAsState()
    val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.backgound))
    val progress by animateLottieCompositionAsState(
        composition = composition.value,
        iterations = LottieConstants.IterateForever
    )
    val modifier = Modifier.padding(5.dp)
//    Box(modifier = Modifier.fillMaxSize()) {
//        LottieAnimation(
//            modifier = Modifier.fillMaxSize(),
//            composition = composition.value,
//            progress = { progress }
//        )
    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                viewModel.isInformation = false
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.changeIsInformation() }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    tint = Color.White,
                    contentDescription = "add"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .background(Background_main)
                .clip(RoundedCornerShape(15.dp)),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (viewModel.isAlertDialog) {
                AlertMessage(
                    viewModel = viewModel,
                    cardModel = stateCard,
                    navController = navController
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = stateCard.imageId),  //переключается только после написания чего либо в других полях
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )
                Button(
                    onClick = {
                        viewModel.changeIndex()
                        viewModel.setImageId()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Title_main,
                        contentColor = text_auth
                    ),
                    modifier = modifier
                ) {
                    Text(text = "Следущая")
                }
            }
            Box(modifier = modifier) {
                Card(modifier = Modifier, shape = RoundedCornerShape(20.dp)) {
                    TextField(
                        value = stateCard.title, {
                            viewModel.setTitle(it)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = text_auth,
                            backgroundColor = EditorInput
                        ),
                        textStyle = TextStyle(fontSize = 18.sp),
                        maxLines = 10,
                        placeholder = { Text(text = "Название") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Box(modifier = modifier) {
                Card(modifier = Modifier, shape = RoundedCornerShape(20.dp)) {
                    TextField(
                        value = stateCard.body, {
                            viewModel.setBody(it)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = text_auth,
                            backgroundColor = EditorInput
                        ),
                        textStyle = TextStyle(fontSize = 18.sp),
                        maxLines = 10,
                        placeholder = { Text(text = "Описание") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Box(modifier = modifier) {
                Card(modifier = Modifier, shape = RoundedCornerShape(20.dp)) {
                    TextField(
                        value = stateCard.formula, {
                            viewModel.setFormula(it)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = text_auth,
                            backgroundColor = EditorInput
                        ),
                        textStyle = TextStyle(fontSize = 18.sp),
                        maxLines = 1,
                        placeholder = { Text(text = "Формула") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Box(modifier = modifier) {
                Card(modifier = Modifier, shape = RoundedCornerShape(20.dp)) {
                    TextField(
                        value = stateCard.arrayhint, {
                            viewModel.setArrayhint(it)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = text_auth,
                            backgroundColor = EditorInput
                        ),
                        textStyle = TextStyle(fontSize = 18.sp),
                        maxLines = 1,
                        placeholder = { Text(text = "Название входящих данных") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Button(
                onClick = {
                    viewModel.addCard(
                        stateCard
                    ) {
                        navController.navigate(Screens.MainScreen.rout) {
                            popUpTo(Screens.MainScreen.rout) {
                                inclusive = true
                            }
                        }
                    }
                },
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = EditorButton,
                    contentColor = text_auth
                )
            ) {
                Text(text = "Создать")
            }
            Text(
                text = viewModel.errorMessage,
                modifier = modifier,
                color = Color.Red,
                )
        }
        if (viewModel.isInformation) {
            InformationItem()
        }
    }
}
//}
