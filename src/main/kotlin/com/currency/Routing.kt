package com.currency

import com.currency.routes.apiRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(HttpStatusCode.NotFound,"Not Found")
        }
        apiRoutes()
    }
}
