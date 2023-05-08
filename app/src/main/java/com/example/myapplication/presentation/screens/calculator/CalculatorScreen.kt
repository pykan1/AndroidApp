package com.example.myapplication.presantation.screens.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.presantation.navigation.Screens
import com.example.myapplication.presantation.ui.components.Text_fields
import com.example.myapplication.presantation.ui.theme.Background_main
import com.example.myapplication.presantation.ui.theme.Body_main
import com.example.myapplication.presantation.ui.theme.Title_main

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CalculatorScreen(card: CardModel) {
    val viewModel = hiltViewModel<CalculatorViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current
    val mContext = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val stateHome by viewModel.calc.collectAsState()
    viewModel.getStrings(cardModel = card)
    val localFocusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background_main)
            .padding(5.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .defaultMinSize(minHeight = 34.dp, minWidth = 70.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Title_main,
            ) {
                Text(
                    text = card.title,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 30.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Body_main,
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            viewModel.isExpandedChange()
                        }
                        .padding(5.dp),
                    fontSize = 18.sp,
                    maxLines = if (stateHome.isExpanded) 10 else 2,
                    text = card.body,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "Формула - ${card.formula}",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .fillMaxWidth()
            )
            if (stateHome.isButton) {
                Text_fields(viewModel)
            } else {
                Text_fields(viewModel)
            }
            Button(
                onClick = {
                    keyboardController?.hide()
                    viewModel.getAnswer(card)
                    viewModel.getStrings(card)
                    viewModel.isButtonChange()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Посчитать")
            }
            Text(
                modifier = Modifier
                    .clickable {
                        clipboardManager.setText(AnnotatedString((stateHome.answear)))
//                        RegisterAuthModel().makeToast(mContext, "Ответ скопирован")
                    }
                    .padding(top = 30.dp),
                text = stateHome.answear,
                fontSize = MaterialTheme.typography.h4.fontSize,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
