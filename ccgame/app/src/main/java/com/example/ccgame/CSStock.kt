package com.example.ccgame

data class CSStock(
    var createdAt: Long = 0,
    val open: Float,
    val close: Float,
    val shadowHigh: Float,
    val shadowLow: Float
)