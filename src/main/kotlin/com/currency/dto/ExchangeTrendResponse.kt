package com.currency.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeTrendResponse (
    val amount: Double,
    var base: String,
    var symbol: String,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("end_date")
    val endDate: String,
    @SerialName("net_change")
    val netChange: String,
    val result: String
)