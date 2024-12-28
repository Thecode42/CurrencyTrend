package com.currency.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRangeRequest (
    val base: String,
    val symbol: String,
    @SerialName("start_date")
    val startDate: String = "",
    @SerialName("end_date")
    val endDate: String = ""
)