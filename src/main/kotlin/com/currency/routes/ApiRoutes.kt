package com.currency.routes
import com.currency.dto.ConvertRequest
import com.currency.dto.ExchangeRangeRequest
import com.currency.services.CurrencyApiService
import com.currency.services.TrendApiService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.apiRoutes() {
    val trendApiService = TrendApiService()
    val currencyApiService = CurrencyApiService()
    route("/api") {
        accept(ContentType.Application.Json) {
            post("/getTrend") {
                try {
                    val request = call.receive<ExchangeRangeRequest>()
                    call.respond(trendApiService.getTrend(request))
                }catch (e: Exception){
                    call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request body")
                }
            }
            post("/getConversion"){
                try {
                    val request = call.receive<ConvertRequest>()
                    call.respond(currencyApiService.getCurrentCurrency(request))
                }catch (e: Exception){
                    call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request body")
                }
            }
        }
    }
}