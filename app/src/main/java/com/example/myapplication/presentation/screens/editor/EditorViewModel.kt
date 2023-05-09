package com.example.myapplication.presantation.screens.editor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.domain.usecase.CommitJsonUseCase
import com.example.myapplication.domain.usecase.card.AddCardUseCase
import com.example.myapplication.domain.usecase.card.GetAllCardsUseCase
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val addCardUseCase: AddCardUseCase,
    private val commitJsonUseCase: CommitJsonUseCase,
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val getAllUserUseCase: GetAllUserUseCase,
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

    fun testCard() {
        viewModelScope.launch {
            cardModel.emit(
                CardModel(
                    title = "Сила тока",
                    body = "Сила тока — это физическая величина, которая показывает, какой заряд прошел через проводник за единицу времени.",
                    formula = "q:t",
                    arrayhint = "Электрический заряд, время"
                )
            )
        }
        isInformation = false
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
                    commitJsonUseCase.invoke()
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
