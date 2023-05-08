package com.example.myapplication.presentation.ui.components.Card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CardViewModel: ViewModel() {
    var isExpand by mutableStateOf(false)

    fun isExpandChange() {
        isExpand = !isExpand
    }
}