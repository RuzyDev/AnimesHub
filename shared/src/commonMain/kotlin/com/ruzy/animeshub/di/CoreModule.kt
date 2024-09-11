package com.ruzy.animeshub.di

import com.ruzy.animeshub.database.AnimesHubDatabase
import com.ruzy.animeshub.domain.interactor.InsertRandomAnime
import com.ruzy.animeshub.domain.observers.ObserveAnimes
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.domain.repository.impl.TopRepositoryImpl
import com.ruzy.animeshub.util.AppCoroutineDispatchers
import com.ruzy.animeshub.util.datastore.AnimesHubDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val coreModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single {
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )
    }
    single { AnimesHubDataStore(get()) }

    single<TopRepository> {
        TopRepositoryImpl(
            topDataSource = get(),
            animeQueries = get<AnimesHubDatabase>().animeQueries,
            mangaQueries = get<AnimesHubDatabase>().mangaQueries,
            imageMangaQueries = get<AnimesHubDatabase>().imageMangaQueries,
            imageAnimeQueries = get<AnimesHubDatabase>().imageAnimeQueries,
        )
    }

    //Domains
    single { ObserveAnimes(get()) }
    single { InsertRandomAnime(get(), get()) }
}