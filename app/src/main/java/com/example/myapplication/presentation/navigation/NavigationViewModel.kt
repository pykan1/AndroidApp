package com.example.myapplication.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.local.model.SettingsModel
import com.example.myapplication.domain.usecase.user.CommitUserSettingsUseCase
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase,
    private val commitUserSettingsUseCase: CommitUserSettingsUseCase
) : ViewModel() {
    val user = runBlocking { getAllUserUseCase.invoke() }
    var settings = MutableStateFlow(SettingsModel())

    fun initSettings() {
        CoroutineScope(Dispatchers.IO).launch {
            settings.emit(
                Gson().fromJson(user[user.size-1].settings, SettingsModel::class.java)
            )
        }
    }

    fun bigDecimalModeChange() {
        CoroutineScope(Dispatchers.IO).launch {
            settings.emit(
                SettingsModel(
                    bigDecimalMode = !settings.value.bigDecimalMode
                )
            )
            commitUserSettingsUseCase.invoke(
                newSettings = Gson().toJson(settings.value),
                refreshToken = user[user.size-1].refresh_token
            )
        }
    }

    fun convertScientificToDecimal(number: Double): String {
        return number.toBigDecimal().stripTrailingZeros().toPlainString()
    }
}