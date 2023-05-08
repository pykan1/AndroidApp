package com.example.myapplication.presantation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presantation.screens.calculator.CalculatorViewModel
import com.example.myapplication.presantation.ui.theme.TextInput
import com.example.myapplication.presantation.ui.theme.text_auth

@Composable
fun Text_fields(ViewModel: CalculatorViewModel) {
    ViewModel.array.forEachIndexed { index, it ->
        val mut = remember { mutableStateOf("") }
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(10.dp)
        ) {
            TextField(
                value = mut.value,
                onValueChange = {
                    mut.value = it
                    ViewModel.array_strings[index] = it
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                mut.value = ""
                            }
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = text_auth,
                    backgroundColor = TextInput
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                maxLines = 1,
                placeholder = { Text(text = it) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}