package com.currency

import com.currency.serialization.AppConfig
import com.currency.serialization.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    AppConfig.config = environment.config
    configureSerialization()
    configureRouting()
}
