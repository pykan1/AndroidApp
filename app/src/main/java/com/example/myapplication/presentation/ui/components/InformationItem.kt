package com.example.myapplication.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.presantation.ui.theme.Background_main

@Composable
fun InformationItem() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 45.dp, bottom = 350.dp, end = 45.dp, top = 45.dp)
            .background(Color.LightGray)
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = """В поле "формула" вы прописываете формулу так как оно есть. Например: a*b*c.
                |В поле "Название входящих данных" вы пишете название переменных через запятую.
                |В примере выше приведена формула "a*b*c", соответсвенно в это поле мы должны
                |записать что то типо "Длинна, Высота, Ширина". Надеюсь это вам поможет
            """.trimMargin(),
//            fontSize = 15.dp,
            color = Color.Black,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Medium,
        )
    }
}