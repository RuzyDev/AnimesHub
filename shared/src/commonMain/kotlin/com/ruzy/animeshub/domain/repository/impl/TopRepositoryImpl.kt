package com.ruzy.animeshub.domain.repository.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ruzy.animeshub.db.anime.AnimeQueries
import com.ruzy.animeshub.db.anime.ImageAnimeEntity
import com.ruzy.animeshub.db.anime.ImageAnimeQueries
import com.ruzy.animeshub.db.manga.ImageMangaEntity
import com.ruzy.animeshub.db.manga.ImageMangaQueries
import com.ruzy.animeshub.db.manga.MangaQueries
import com.ruzy.animeshub.db.anime.RankingAnimeQueries
import com.ruzy.animeshub.db.manga.RankingMangaQueries
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.anime.AnimeDetails
import com.ruzy.animeshub.model.anime.toAnimeDetails
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import com.ruzy.animeshub.model.anime.toExternalModel
import com.ruzy.animeshub.model.manga.MangaDetails
import com.ruzy.animeshub.model.manga.toExternalModel
import com.ruzy.animeshub.model.manga.toMangaDetails
import com.ruzy.animeshub.network.service.TopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TopRepositoryImpl(
    private val topService: TopService,
    private val animeQueries: AnimeQueries,
    private val mangaQueries: MangaQueries,
    private val imageMangaQueries: ImageMangaQueries,
    private val imageAnimeQueries: ImageAnimeQueries,
    private val rankingAnimeQueries: RankingAnimeQueries,
    private val rankingMangaQueries: RankingMangaQueries
) : TopRepository {

    override suspend fun updateTopAnimes(type: TypeRakingAnime) {
        val animes = topService.getTopAnime(type.type)
        val entity = animes.data
        entity.forEach {
            rankingAnimeQueries.insertOrUpdate(it.malId, type.type, it.rank)
            animeQueries.insertOrUpdate(id = it.malId, title = it.title)
            imageAnimeQueries.insertOrUpdate(
                id_anime = it.malId,
                url = it.networkAnimeImagesTop?.jpg?.imageUrl
            )
        }
    }

    override suspend fun updateTopMangas(type: TypeRakingManga) {
        val mangas = topService.getTopAnime(type.type)
        val entity = mangas.data
        entity.forEach {
            rankingMangaQueries.insertOrUpdate(it.malId, type.type, it.rank)
            mangaQueries.insertOrUpdate(id = it.malId, title = it.title)
            imageMangaQueries.insertOrUpdate(
                id_manga = it.malId,
                url = it.networkAnimeImagesTop?.jpg?.imageUrl
            )
        }
    }

    override suspend fun getTopAnimes(type: TypeRakingAnime, page: Long): List<AnimeDetails> =
        animeQueries.getTopAnimes(type.type, page).executeAsList().map { anime ->
            val images = imageAnimeQueries.getByIdAnime(anime.id).executeAsList()
                .map(ImageAnimeEntity::toExternalModel)
            anime.toAnimeDetails(images)
        }

    override suspend fun getTopMangas(type: TypeRakingManga, page: Long): List<MangaDetails> =
        mangaQueries.getTopMangas(type.type, page).executeAsList().map { manga ->
            val images = imageMangaQueries.getByIdManga(manga.id).executeAsList()
                .map(ImageMangaEntity::toExternalModel)
            manga.toMangaDetails(images)
        }

    override fun observeTopAnimes(type: TypeRakingAnime, page: Long): Flow<List<AnimeDetails>> =
        animeQueries.getTopAnimes(type.type, page).asFlow().mapToList(Dispatchers.IO).map {
            it.map { anime ->
                val images = imageAnimeQueries.getByIdAnime(anime.id).executeAsList()
                    .map(ImageAnimeEntity::toExternalModel)
                anime.toAnimeDetails(images)
            }
        }

    override fun observeTopMangas(type: TypeRakingManga, page: Long): Flow<List<MangaDetails>> =
        mangaQueries.getTopMangas(type.type, page).asFlow().mapToList(Dispatchers.IO).map {
            it.map { manga ->
                val images = imageMangaQueries.getByIdManga(manga.id).executeAsList()
                    .map(ImageMangaEntity::toExternalModel)
                manga.toMangaDetails(images)
            }
        }

}
