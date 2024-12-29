package com.currency

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.*


class ApplicationTest {
    @Test
    fun `test application startup`() = testApplication {
        application {
            module()
        }
        client.get("/").apply {
            assertEquals(404, status.value)
        }
    }
}