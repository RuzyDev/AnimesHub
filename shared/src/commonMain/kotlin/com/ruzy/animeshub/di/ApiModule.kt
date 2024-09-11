package com.ruzy.animeshub.di

import com.ruzy.animeshub.network.KtorHttpClient
import com.ruzy.animeshub.network.datasource.TopDataSource
import com.ruzy.animeshub.network.service.TopService
import org.koin.dsl.module

fun apiModule(enableNetworkLogs: Boolean) = module {
    single { KtorHttpClient(get()).httpClient(enableNetworkLogs) }

    single<TopService> { TopDataSource(get()) }
}