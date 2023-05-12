package com.example.myapplication.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.presantation.screens.editor.EditorViewModel
import com.example.myapplication.presantation.ui.theme.Backgound_auth
import com.example.myapplication.presantation.ui.theme.Button_auth
import com.example.myapplication.presantation.ui.theme.bottomnav
import com.example.myapplication.presantation.ui.theme.text_auth

@Composable
fun InformationItem(
    addViewModel: EditorViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Backgound_auth),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = """  В поле "формула" вы прописываете формулу так как оно есть. Например: a*b*c.
                |В поле "Название входящих данных" вы пишете название переменных через запятую.
                |В примере выше приведена формула "a*b*c", соответсвенно в это поле мы должны
                |записать что то типо "Длинна, Высота, Ширина". Надеюсь это вам поможет.
                |
                |   Приложение поддерживает использование sin, cos, tg и cos. Просто пишите нужную
                |вам триганометрическую функцию и рядом с ней !!БЕЗ пробела!! пишите переменную.
                |функции принимают в себя еденицы измерения РАДИАНЫ!
                |Например: sinx/cosy
                |
            """.trimMargin(),
//            fontSize = 15.dp,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium,
            )
            Button(
                onClick = {
                    addViewModel.testCard()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Button_auth,
                    contentColor = text_auth
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Text(text = "Использовать пример")
            }
        }
    }
}