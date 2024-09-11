package com.ruzy.animeshub.domain.repository.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ruzy.animeshub.db.AnimeQueries
import com.ruzy.animeshub.db.GetTopAnimes
import com.ruzy.animeshub.db.ImageAnimeEntity
import com.ruzy.animeshub.db.ImageAnimeQueries
import com.ruzy.animeshub.db.ImageMangaEntity
import com.ruzy.animeshub.db.ImageMangaQueries
import com.ruzy.animeshub.db.MangaQueries
import com.ruzy.animeshub.db.RankingAnimeQueries
import com.ruzy.animeshub.db.RankingMangaQueries
import com.ruzy.animeshub.domain.repository.TopRepository
import com.ruzy.animeshub.model.anime.AnimeDetails
import com.ruzy.animeshub.model.ranking.TypeRakingAnime
import com.ruzy.animeshub.model.ranking.TypeRakingManga
import com.ruzy.animeshub.model.anime.toExternalModel
import com.ruzy.animeshub.model.manga.MangaDetails
import com.ruzy.animeshub.model.manga.toExternalModel
import com.ruzy.animeshub.model.manga.toMangaDetails
import com.ruzy.animeshub.network.datasource.TopDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TopRepositoryImpl(
    private val topDataSource: TopDataSource,
    private val animeQueries: AnimeQueries,
    private val mangaQueries: MangaQueries,
    private val imageMangaQueries: ImageMangaQueries,
    private val imageAnimeQueries: ImageAnimeQueries,
    private val rankingAnimeQueries: RankingAnimeQueries,
    private val rankingMangaQueries: RankingMangaQueries
) : TopRepository {

    override suspend fun updateTopAnimes(type: TypeRakingAnime) {
        val animes = topDataSource.getTopAnime(type.type)
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
        val mangas = topDataSource.getTopAnime(type.type)
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

    override fun observeTopAnimes(type: TypeRakingAnime): Flow<List<AnimeDetails>> =
        animeQueries.getTopAnimes(type.type).asFlow().mapToList(Dispatchers.IO).map {
            it.map { anime ->
                val images = imageAnimeQueries.getByIdAnime(it.id).executeAsList()
                    .map(ImageAnimeEntity::toExternalModel)
                mapToAnimeDetails(anime)
            }
        }

    override fun observeTopMangas(type: TypeRakingManga): Flow<List<MangaDetails>> =
        mangaQueries.selectAll().asFlow().mapToList(Dispatchers.IO).map {
            it.map {
                val images = imageMangaQueries.getByIdManga(it.id).executeAsList()
                    .map(ImageMangaEntity::toExternalModel)
                it.toMangaDetails(images)
            }
        }


    fun mapToAnimeDetails(anime: GetTopAnimes): PostWithTags {
        val postId = cursor.getLong(cursor.getColumnIndex("postId"))
        val postTitle = cursor.getString(cursor.getColumnIndex("postTitle"))

        val tags = mutableListOf<Tag>()
        do {
            val tagId = cursor.getLong(cursor.getColumnIndex("tagId"))
            val tagName = cursor.getString(cursor.getColumnIndex("tagName"))
            tags.add(Tag(tagId, tagName))
        } while (cursor.moveToNext())

        return PostWithTags(postId, postTitle, tags)
    }

}
