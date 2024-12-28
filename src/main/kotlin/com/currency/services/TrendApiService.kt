package com.currency.services
import com.currency.serialization.client
import com.currency.dto.ExchangeRangeClientResponse
import com.currency.dto.ExchangeRangeRequest
import com.currency.dto.ExchangeTrendResponse
import com.currency.utils.ValidateUtil
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class TrendApiService{
    suspend fun getTrend(exchangeRangeRequest: ExchangeRangeRequest): ExchangeTrendResponse {
        val responseClient:ExchangeRangeClientResponse = getExchangeRangeClientResponse(exchangeRangeRequest).await()
        val responseCurrencies:Map<String, String> = getCurrencyClientResponse().await()
        val response = calculateExchangeRateChange(responseClient, exchangeRangeRequest.symbol)
        response.base += " - " + responseCurrencies.getOrDefault(responseClient.base,responseClient.base)
        response.symbol += " - " + responseCurrencies.getOrDefault(exchangeRangeRequest.symbol,exchangeRangeRequest.symbol)
        return response
    }
    private suspend fun getExchangeRangeClientResponse(exchangeRangeRequest: ExchangeRangeRequest) = coroutineScope {
        val apiUrl: String = ValidateUtil.getUrlApi("api.url_trend2Currency") + exchangeRangeRequest.startDate + ".." + exchangeRangeRequest.endDate
        async{
            client.get(apiUrl) {
                contentType(ContentType.Application.Json)
                parameter("base", exchangeRangeRequest.base)
                parameter("symbols", exchangeRangeRequest.symbol)
            }.body<ExchangeRangeClientResponse>()
        }
    }
    private suspend fun getCurrencyClientResponse() = coroutineScope {
        val apiUrl: String = ValidateUtil.getUrlApi("api.url_currencies")
        async {
            client.get(apiUrl){contentType(ContentType.Application.Json)}.body<Map<String, String>>()
        }
    }
    private fun calculateExchangeRateChange(responseClient: ExchangeRangeClientResponse, symbol: String): ExchangeTrendResponse{
        val sortedDates = responseClient.rates.keys.sorted()
        if (sortedDates.isEmpty())
            throw Exception("No exchange rates available to calculate changes.")
        val initialDate = sortedDates.first()
        val finalDate = sortedDates.last()
        val initialRate = responseClient.rates[initialDate]?.get(symbol)
        val finalRate = responseClient.rates[finalDate]?.get(symbol)
        if (initialRate == null || finalRate == null)
            throw Exception("Exchange rates for $symbol are missing in the provided data.")
        val netChange = finalRate - initialRate
        val percentageChange = (netChange / initialRate) * 100
        val changeDescription = if (netChange > 0) "has grown" else "has decreased"
        val result = """
        Analysis of ${responseClient.base} to $symbol exchange rate:
        The ${responseClient.base} $changeDescription against the $symbol by ${"%.2f".format(percentageChange)}% 
        Net Change: ${"%.5f".format(netChange)} $symbol per ${responseClient.base}.
        """.trimIndent()
        val response = ExchangeTrendResponse(responseClient.amount,responseClient.base,symbol,
            responseClient.startDate, responseClient.endDate, String.format("%.5f", netChange),result)
        return response
    }
}