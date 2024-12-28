package com.currency.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConvertRequest (
    val base: String,
    val symbol: String,
    val amount: Double
)