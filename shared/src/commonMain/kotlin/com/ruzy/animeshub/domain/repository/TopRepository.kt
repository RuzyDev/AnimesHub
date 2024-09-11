package com.ruzy.animeshub.domain.repository

import com.ruzy.animeshub.model.anime.AnimeDetails
import com.ruzy.animeshub.model.manga.MangaDetails
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import com.ruzy.animeshub.network.models.top.anime.NetworkTopAnime
import kotlinx.coroutines.flow.Flow

interface TopRepository {

    suspend fun updateTopAnimes(type: TypeRakingAnime)
    suspend fun updateTopMangas(type: TypeRakingManga)
    fun observeTopAnimes(type: TypeRakingAnime): Flow<List<AnimeDetails>>
    fun observeTopMangas(type: TypeRakingManga): Flow<List<MangaDetails>>
}
