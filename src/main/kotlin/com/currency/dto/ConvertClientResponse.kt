package com.currency.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConvertClientResponse(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)