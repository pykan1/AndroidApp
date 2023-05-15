package com.example.myapplication.presantation.screens.calculator2

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.presentation.navigation.NavigationViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class Calculator2ViewModel : ViewModel() {
    var calculation by mutableStateOf("")
    var isEqual by mutableStateOf(false)

    fun makeToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun count(navigationViewModel: NavigationViewModel) {
        try {
            //                        if ("E" in result) {
//                            val eStart = result.indexOf("E", 0)
//                            val eNum = result.substring(eStart+1).toInt()
//                            Log.d("11", eNum.toString())
//                            var subResult = result.substring(0, eStart)
//                            repeat(eNum) {
//                                subResult+="0"
//                            }
//                            subResult.replaceFirst(".", "")
//                        } else {
            calculation = if (navigationViewModel.settings.value.bigDecimalMode) {
                navigationViewModel.convertScientificToDecimal(
                    ExpressionBuilder(calculation).build().evaluate()
                )
            } else ExpressionBuilder(calculation).build().evaluate().toString()

//                        }
        } catch (e: Exception) {
            calculation = "Error"
        }
    }

}