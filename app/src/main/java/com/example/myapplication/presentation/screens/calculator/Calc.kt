package com.example.myapplication.presentation.screens.calculator

data class Calc(
    var isExpanded: Boolean = false,
    var isButton: Boolean = false,
    var answear: String = "",
    var isNothing: Boolean = false
)