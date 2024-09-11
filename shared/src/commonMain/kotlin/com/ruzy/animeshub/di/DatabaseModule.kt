package com.ruzy.animeshub.di

import com.ruzy.animeshub.database.AnimesHubDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AnimesHubDatabase.invoke(driver = get()) }
}