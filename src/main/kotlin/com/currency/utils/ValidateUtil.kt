package com.currency.utils

import com.currency.serialization.AppConfig

object ValidateUtil {
    fun getUrlApi(parameterName:String): String{
        return AppConfig.config.property("api.url_service").getString() + AppConfig.config.property(parameterName).getString()
    }
}