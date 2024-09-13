package com.ruzy.animeshub.di

import com.ruzy.animeshub.database.AnimesHubDatabase
import com.ruzy.animeshub.domain.interactor.anime.GetTopAnimeWithPage
import com.ruzy.animeshub.domain.interactor.anime.UpdateTopAnimes
import com.ruzy.animeshub.domain.interactor.manga.GetTopMangaWithPage
import com.ruzy.animeshub.domain.interactor.manga.UpdateTopMangas
import com.ruzy.animeshub.domain.observers.ObserveTopAnimes
import com.ruzy.animeshub.domain.observers.ObserveTopMangas
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
            topService = get(),
            animeQueries = get<AnimesHubDatabase>().animeQueries,
            mangaQueries = get<AnimesHubDatabase>().mangaQueries,
            imageMangaQueries = get<AnimesHubDatabase>().imageMangaQueries,
            imageAnimeQueries = get<AnimesHubDatabase>().imageAnimeQueries,
            rankingAnimeQueries = get<AnimesHubDatabase>().rankingAnimeQueries,
            rankingMangaQueries = get<AnimesHubDatabase>().rankingMangaQueries
        )
    }

    //----------Domains--------------
    //Anime
    single { ObserveTopAnimes(get()) }
    single { UpdateTopAnimes(get(), get()) }
    single { GetTopAnimeWithPage(get(), get()) }
    //Manga
    single { ObserveTopMangas(get()) }
    single { UpdateTopMangas(get(), get()) }
    single { GetTopMangaWithPage(get(), get()) }
}