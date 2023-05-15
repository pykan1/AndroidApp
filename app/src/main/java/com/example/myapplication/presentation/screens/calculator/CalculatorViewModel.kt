package com.example.myapplication.presantation.screens.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.presentation.screens.calculator.Calc
import com.example.myapplication.data.local.model.CardModel
import com.example.myapplication.presentation.navigation.NavigationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel: ViewModel() {
    var calc = MutableStateFlow(Calc())

    fun isNothingChange(cardModel: CardModel) {
        if (cardModel.title == "{title}") {
            CoroutineScope(Dispatchers.IO).launch {
                calc.emit(
                    Calc(
                        answear = calc.value.answear,
                        isExpanded = calc.value.isExpanded,
                        isButton = calc.value.isButton,
                        isNothing = !calc.value.isNothing
                    )
                )
            }
        }
    }

    var array = listOf<String>()
    var array_strings = arrayOf<String>()
    fun getStrings(cardModel: CardModel) {
        isNothingChange(cardModel)
        var arrayStrings = arrayOf<String>()
        array = cardModel.arrayhint.split(",")
        array.forEach { _ ->
            arrayStrings += ""
        }
        array_strings = arrayStrings
    }

    fun getAnswer(cardModel: CardModel, navigationViewModel: NavigationViewModel) {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        var formula = cardModel.formula.replace(":", "/")
        Log.d("11", formula)
        var char = arrayOf<String>()
        formula.forEachIndexed { index, i ->
            try {
                if (i in alphabet && formula[index+1] !in alphabet) {
                    char += i.toString()
                }
            } catch (e: Exception) {
                char += i.toString()
            }
        }
//        for (i in formula) {
//            if (i in alphabet) {
//                char += i.toString()
//            }
//        }
        Log.d("11", array_strings.toString())
        array_strings.forEachIndexed { index, string_item ->
            try {
                Log.d("11", string_item)
                formula = formula.replace(char[index], string_item)
            } catch (e: Exception) {
                Log.d("11", e.message.toString())
            }
        }
        try {
            val ex = ExpressionBuilder(formula).build()
            val result = if (navigationViewModel.settings.value.bigDecimalMode) {
                navigationViewModel.convertScientificToDecimal(ex.evaluate())
            } else ex.evaluate()

            calc.value.answear = result.toString()

        } catch (e: Exception) {
            Log.d("11", "${e.message}")
        }

    }

    fun isExpandedChange() {
        CoroutineScope(Dispatchers.IO).launch {
            calc.emit(
                Calc(
                    answear = calc.value.answear,
                    isButton = calc.value.isButton,
                    isExpanded = !calc.value.isExpanded,
                    isNothing = calc.value.isNothing
                )
            )
        }
    }

    fun isButtonChange() {
        CoroutineScope(Dispatchers.IO).launch {
            calc.emit(
                Calc(
                    answear = calc.value.answear,
                    isButton = !calc.value.isButton,
                    isExpanded = calc.value.isExpanded,
                    isNothing = calc.value.isNothing
                )
            )
        }
    }
}