package com.example.myapplication.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase
) : ViewModel() {
    val user = runBlocking { getAllUserUseCase.invoke() }.isEmpty()
    var isBigDecimal by mutableStateOf(false)

    fun isBigDecimalChange() {
        isBigDecimal = !isBigDecimal
    }

    fun convertScientificToDecimal(number: Double): String {
        return number.toBigDecimal().stripTrailingZeros().toPlainString()
    }
}