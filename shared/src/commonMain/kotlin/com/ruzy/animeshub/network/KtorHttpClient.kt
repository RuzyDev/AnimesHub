package com.ruzy.animeshub.network

import com.ruzy.animeshub.util.ConstantsShared.BASE_URL
import com.ruzy.animeshub.util.datastore.AnimesHubDataStore
import com.ruzy.animeshub.util.log.CommonLoggerImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorHttpClient(
    private val animeshubDataStore: AnimesHubDataStore
) {
    fun httpClient(enableNetworkLogs: Boolean) = HttpClient {
        expectSuccess = false

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
            }
        }

        if (enableNetworkLogs) {
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        CommonLoggerImpl().log(tag = "Http Client", message = message)
                    }
                }
            }
        }

        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("AppDebug HTTP ResponseObserver status: ${response.status.value}")
            }
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value

                if (statusCode == 401) {

                }

                /*
                                    when (statusCode) {
                                        in 300..399 -> throw RedirectResponseException(response)
                                        in 400..499 -> throw ClientRequestException(response)
                                        in 500..599 -> throw ServerResponseException(response)
                                    }

                                    if (statusCode >= 600) {
                                        throw ResponseException(response)
                                    }
                                }

                                handleResponseException { cause: Throwable ->
                                    throw cause
                                }*/
            }
        }

        install(Logging) {
            //  logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("AppDebug KtorHttpClient message:$message")
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}