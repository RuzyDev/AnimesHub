package com.ruzy.animeshub.model.anime

import com.ruzy.animeshub.db.anime.GetTopAnimes
import com.ruzy.animeshub.model.ranking.TypeRakingAnime

data class AnimeDetails(
    val id: Long,
    val title: String,
    val rank: Long?,
    val rank_type: TypeRakingAnime?,
    val images: List<ImageAnime> = emptyList()
)


fun GetTopAnimes.toAnimeDetails(listImages: List<ImageAnime>) =
    AnimeDetails(
        id = id,
        title = title ?: "Not found",
        images = listImages,
        rank = ranking_value,
        rank_type = TypeRakingAnime.getType(ranking_type)
    )