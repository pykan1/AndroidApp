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
import com.example.myapplication.domain.usecase.CommitJsonUseCase
import com.example.myapplication.domain.usecase.card.DeleteCardUseCase
import com.example.myapplication.domain.usecase.card.FindByTitleUseCase
import com.example.myapplication.domain.usecase.card.GetAllCardsUseCase
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    private val getAllUserUseCase: GetAllUserUseCase,
    private val findByTitleUseCase: FindByTitleUseCase,
    private val commitJsonUseCase: CommitJsonUseCase
) : ViewModel() {
    var subTitle by mutableStateOf("")
    var data: List<UserModel> = emptyList()
    var isExpand by mutableStateOf(false)
    private val _cards = MutableLiveData<List<CardModel>>()
    val cards: LiveData<List<CardModel>>
        get() = _cards

    init {
        getAllCards()
    }

    fun deleteCard(cardModel: CardModel) {
        viewModelScope.launch {
            deleteCardUseCase.invoke(cardModel).let {
                _cards.postValue(_cards.value!!.filterNot { it == cardModel })
            }
            commitJsonUseCase.invoke()
        }
    }

    fun getAllCards() {
        viewModelScope.launch {
            getAllCardsUseCase.invoke().let {
                _cards.postValue(it)
            }
        }
    }

//    fun searchByTitle(subTitle: String) {
//        viewModelScope.launch {
//            _cards.postValue(findByTitleUseCase.invoke(subTitle))
//        }
//    }

    fun searchByTitle(subTitle: String) {
        viewModelScope.launch {
            _cards.postValue(_cards.value!!.filter { subTitle.lowercase() in it.title.lowercase() })
        }
    }

    fun setSubString(str: String) {
        subTitle = str
    }
}