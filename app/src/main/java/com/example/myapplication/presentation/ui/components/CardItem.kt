package com.example.myapplication.presantation.ui.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.domain.model.CardModel
import com.example.myapplication.presantation.screens.main.MainViewModel
import com.example.myapplication.presantation.ui.theme.Button_item_row
import com.example.myapplication.presantation.ui.theme.text_auth
import com.example.myapplication.R
import com.example.myapplication.presantation.ui.theme.Background_main
import kotlinx.coroutines.delay
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import java.util.*
import kotlin.concurrent.schedule


@Composable
fun CardItem(navHostController: NavHostController, cardModel: CardModel, viewModel: MainViewModel) {
    val viewModelOnly = hiltViewModel<MainViewModel>()
    androidx.compose.material.Card(
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(15.dp)
    ) {
        val delete = SwipeAction(
            onSwipe = {
                viewModel.deleteCard(cardModel)
            },
            icon = {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = null,
                    tint = Color.DarkGray
                )
            },
            background = Color.Red,
        )
        SwipeableActionsBox(
            modifier = Modifier
                .fillMaxSize()
                .background(Background_main)
                .clip(RoundedCornerShape(15.dp)),
            startActions = listOf(delete)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = cardModel.imageId),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(3.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                ) {
                    Text(
                        text = cardModel.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Text(
                        modifier = Modifier.clickable {
                            viewModelOnly.isExpandChange()
                        },
                        fontSize = 15.sp,
                        maxLines = if (viewModel.isExpand) 10 else 1,
                        text = cardModel.body,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                navHostController.navigate(
                                    "calc_screen/" +
                                            "${cardModel.title}/" +
                                            "${cardModel.body}/" +
                                            "${cardModel.formula}/" +
                                            cardModel.arrayhint
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Button_item_row,
                                contentColor = text_auth
                            ),
                        ) {
                            Text(text = "Посчитать")
                        }
                    }
                }
            }
        }
    }
}