package com.example.myapplication.presantation.ui.components


import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.*
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.presantation.screens.editor.EditorViewModel

@Composable
fun AlertMessage(viewModel: EditorViewModel, cardModel: CardModel, navController: NavController) {
    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text(text = "Не повторяйтесь :(")
        },
        text = {
            Text("Данная карточка уже была создана когда то ранее")
        },
        confirmButton = {
            Button(
                onClick = {
                    navController.navigate("calc_screen/" +
                            "${cardModel.title}/" +
                            "${cardModel.body}/" +
                            "${cardModel.formula}/" +
                            cardModel.arrayhint)
                }) {
                Text("Перейти к ней")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.isAlertDialog = false
                }) {
                Text("Понял")
            }
        }
    )
}