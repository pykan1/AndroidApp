package com.example.myapplication.presantation.screens.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.model.Calc
import com.example.myapplication.domain.model.CardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel: ViewModel() {
    var calc = MutableStateFlow(Calc())

    private fun isNothingChange(cardModel: CardModel) {
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

    fun getAnswer(cardModel: CardModel) {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        var formula = cardModel.formula.replace(":", "/")
        Log.d("Mylog", formula)
        var char = arrayOf<String>()
        for (i in formula) {
            if (i in alphabet) {
                char += i.toString()
            }
        }
//                        Замена переменных на действительные числа в formula
        array_strings.forEachIndexed { index, string_item ->
            try {
                Log.d("Mylog", "$index, $string_item, ${array_strings.size}")
                formula = formula.replace(char[index], string_item)
            } catch (e: Exception) {
                Log.d("Mylog", e.message.toString())
            }
        }
        try {
            Log.d("Mylog", formula)
            val ex = ExpressionBuilder(formula).build()
            val result = ex.evaluate()
            calc.value.answear = result.toString()

        } catch (e: Exception) {
            Log.d("Mylog", "${e.message}")
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