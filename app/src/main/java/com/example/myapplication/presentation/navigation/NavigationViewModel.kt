package com.example.myapplication.presentation.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.api.model.UserJsonClass
import com.example.myapplication.data.local.model.SettingsModel
import com.example.myapplication.data.local.model.UserModel
import com.example.myapplication.domain.usecase.user.CommitUserSettingsUseCase
import com.example.myapplication.domain.usecase.user.GetAllUserUseCase
import com.example.myapplication.domain.usecase.user.UpdateSettingsUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val getAllUserUseCase: GetAllUserUseCase,
    private val commitUserSettingsUseCase: CommitUserSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase
) : ViewModel() {
    var settings = MutableStateFlow(SettingsModel())
    var users = runBlocking { getAllUserUseCase.invoke() }

    fun initSettings() {
        CoroutineScope(Dispatchers.IO).launch {
            if (getAllUserUseCase.invoke().isNotEmpty()) {
                val user = async {getAllUserUseCase.invoke()}
                Log.d("11", user.await().size.toString())
                settings.emit(
                    Gson().fromJson(user.await()[user.await().size-1].settings, SettingsModel::class.java)
                )
            }
        }
    }

    fun bigDecimalModeChange() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = async {getAllUserUseCase.invoke()}
                settings.emit(
                    SettingsModel(
                        bigDecimalMode = !settings.value.bigDecimalMode
                    )
                )
                commitUserSettingsUseCase.invoke(
                    newSettings = Gson().toJson(settings.value),
                    refreshToken = user.await()[user.await().size - 1].refresh_token
                )
                updateSettingsUseCase.invoke(
                    UserJsonClass(
                        refresh_token = user.await()[user.await().size-1].refresh_token,
                        settings = Gson().toJson(settings.value)
                    )
                )
            } catch (e: Exception) {
                Log.d("11", e.message.toString())
            }
        }
    }

    fun convertScientificToDecimal(number: Double): String {
        return number.toBigDecimal().stripTrailingZeros().toPlainString()
    }
}