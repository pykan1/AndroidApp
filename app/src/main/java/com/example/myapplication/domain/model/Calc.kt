package com.example.myapplication.domain.model

data class Calc(
    var isExpanded: Boolean = false,
    var isButton: Boolean = false,
    var answear: String = "",
    var isNothing: Boolean = false
)