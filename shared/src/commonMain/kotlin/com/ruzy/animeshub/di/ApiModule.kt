package com.ruzy.animeshub.di

import com.ruzy.animeshub.network.KtorHttpClient
import com.ruzy.animeshub.network.service.impl.TopServiceImpl
import com.ruzy.animeshub.network.service.TopService
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun apiModule(enableNetworkLogs: Boolean) = module {
    single<HttpClient> { KtorHttpClient(get()).httpClient(enableNetworkLogs) }

    single<TopService> { TopServiceImpl(get()) }
}