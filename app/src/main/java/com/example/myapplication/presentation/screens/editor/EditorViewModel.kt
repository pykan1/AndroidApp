package com.example.myapplication.presantation.screens.editor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.usecases.card.AddCardUseCase
import com.example.myapplication.data.usecases.user.CommitUserJsonUseCase
import com.example.myapplication.data.usecases.card.GetAllCardsUseCase
import com.example.myapplication.data.usecases.user.GetAllUserUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val addCardUseCase: AddCardUseCase,
    private val commitUserJsonUseCase: CommitUserJsonUseCase,
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getAllUserUseCase: GetAllUserUseCase
) : ViewModel() {
    var cardModel = MutableStateFlow(CardModel())
    var indexImage = mutableStateOf(0)
    var errorMessage by mutableStateOf("")
    var isAlertDialog by mutableStateOf(false)
    var isInformation by mutableStateOf(false)

    val imageIdList = listOf(
        R.drawable.math,
        R.drawable.physics,
        R.drawable.it
    )

    fun changeIsInformation () {
        isInformation = !isInformation
    }

    fun changeIndex() {
        if (indexImage.value == 2) {
            indexImage.value = 0
        } else {
            indexImage.value++
        }
    }

    fun setImageId() {
        viewModelScope.launch {
            cardModel.emit(
                CardModel(
                    title = cardModel.value.title,
                    imageId = imageIdList[indexImage.value],
                    body = cardModel.value.body,
                    formula = cardModel.value.formula,
                    arrayhint = cardModel.value.arrayhint,
                )
            )
        }
    }

    fun setTitle(title: String) {
        viewModelScope.launch {
            cardModel.emit(
                CardModel(
                    title = title,
                    body = cardModel.value.body,
                    formula = cardModel.value.formula,
                    arrayhint = cardModel.value.arrayhint,
                    imageId = imageIdList[indexImage.value]
                )
            )
        }
    }

    fun setBody(body: String) {
        viewModelScope.launch {
            cardModel.emit(
                CardModel(
                    title = cardModel.value.title,
                    body = body,
                    formula = cardModel.value.formula,
                    arrayhint = cardModel.value.arrayhint,
                    imageId = imageIdList[indexImage.value]
                )
            )
        }
    }

    fun setFormula(formula: String) {
        viewModelScope.launch {
            cardModel.emit(
                CardModel(
                    title = cardModel.value.title,
                    body = cardModel.value.body,
                    formula = formula,
                    arrayhint = cardModel.value.arrayhint,
                    imageId = imageIdList[indexImage.value]
                )
            )
        }
    }

    fun setArrayhint(arrayhint: String) {
        viewModelScope.launch {
            cardModel.emit(
                CardModel(
                    title = cardModel.value.title,
                    body = cardModel.value.body,
                    formula = cardModel.value.formula,
                    arrayhint = arrayhint,
                    imageId = imageIdList[indexImage.value]
                )
            )
        }
    }

    private fun commitJson() {
        viewModelScope.launch {
            val data = getAllCardsUseCase.invoke()
            val user = getAllUserUseCase.invoke()
            commitUserJsonUseCase.invoke(
                UserJsonClass(
                    userJson = Gson().toJson(data.toTypedArray()),
                    refresh_token = user[user.size - 1].refresh_token
                )
            )
        }
    }

    fun addCard(
        cardModel: CardModel,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val cards = getAllCardsUseCase.invoke()
                handlerEditor()
                if (cardModel in cards) isAlertDialog = true
                else {
                    addCardUseCase.invoke(cardModel = cardModel)
                    commitJson()
                    onSuccess()
                }
            } catch (e: Exception) {
                errorMessageText(e.message.toString())
            }
        }
    }

    private fun errorMessageText(text: String) {
        errorMessage = text
    }

    private fun handlerEditor() {
        errorMessage = when {
            cardModel.value.title == "" -> "Название не может быть пустым"
            cardModel.value.formula == "" -> "Формула не может быть пустой"
            cardModel.value.arrayhint == "" -> "Перечень формул не может быть пустым"
            else -> ""
        }
        if (errorMessage != "") {
            throw IllegalArgumentException(errorMessage)
        }
    }
}
