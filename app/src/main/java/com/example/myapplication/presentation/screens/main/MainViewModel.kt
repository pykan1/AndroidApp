package com.example.myapplication.presantation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.data.local.model.UserModel
import com.example.myapplication.data.usecases.user.CommitUserJsonUseCase
import com.example.myapplication.data.usecases.card.DeleteCardUseCase
import com.example.myapplication.data.usecases.card.GetAllCardsUseCase
import com.example.myapplication.data.usecases.user.GetAllUserUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    private val getAllUserUseCase: GetAllUserUseCase,
    private val commitUserJsonUseCase: CommitUserJsonUseCase
) : ViewModel() {
    var data: List<UserModel> = emptyList()
    var isExpand by mutableStateOf(false)
    private val _cards = MutableLiveData<List<CardModel>>()
    val cards: LiveData<List<CardModel>>
        get() = _cards

    init {
        getAllCards()
    }

    private fun commitJson() {
        viewModelScope.launch {
            val data = getAllCardsUseCase.invoke()
            val user = getAllUserUseCase.invoke()
            commitUserJsonUseCase.invoke(UserJsonClass(userJson = Gson().toJson(data.toTypedArray()), refresh_token = user[user.size-1].refresh_token))
        }
    }

    fun deleteCard(cardModel: CardModel) {
        viewModelScope.launch {
            deleteCardUseCase.invoke(cardModel).let {
                _cards.postValue(_cards.value!!.filterNot { it == cardModel })
            }
            commitJson()
        }
    }

    private fun getAllCards() {
        viewModelScope.launch {
            getAllCardsUseCase.invoke().let {
                _cards.postValue(it)
            }
        }
    }
}