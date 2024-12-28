package com.currency.services

import com.currency.dto.ConvertClientResponse
import com.currency.serialization.client
import com.currency.dto.ConvertRequest
import com.currency.utils.ValidateUtil
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyApiService {
    suspend fun getCurrentCurrency(convertRequest: ConvertRequest): Map<String, String> {
        val responseClient:ConvertClientResponse = getConversionClientResponse(convertRequest)
        val rate = responseClient.rates.get(convertRequest.symbol)
        val converterAmount = BigDecimal(convertRequest.amount).multiply(BigDecimal(rate.toString())).setScale(2, RoundingMode.HALF_EVEN)
        return mapOf("base" to convertRequest.base, "symbol" to convertRequest.symbol, "result" to converterAmount.toString())
    }

    private suspend fun getConversionClientResponse(convertRequest: ConvertRequest):ConvertClientResponse {
        val apiUrl: String = ValidateUtil.getUrlApi("api.url_conversion")
        return client.get(apiUrl){
                contentType(ContentType.Application.Json)
                parameter("base", convertRequest.base)
                parameter("symbols", convertRequest.symbol)
            }.body()
    }
}