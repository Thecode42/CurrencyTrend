package com.currency.serialization

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(
            kotlinx.serialization.json.Json {
                prettyPrint = true // Prints JSON in a human-readable, indented format.
                encodeDefaults = true // Includes properties with default values
                ignoreUnknownKeys = true // Ignores keys in the JSON that are not defined in the data class.
                isLenient = true // Allows less strict JSON formats, such as single quotes or extra whitespace.
            })
    }
}