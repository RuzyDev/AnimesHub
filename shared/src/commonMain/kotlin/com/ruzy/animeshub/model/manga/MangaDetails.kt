package com.ruzy.animeshub.model.manga

import com.ruzy.animeshub.db.manga.GetTopMangas
import com.ruzy.animeshub.model.ranking.TypeRakingManga

data class MangaDetails(
    val id: Long,
    val title: String,
    val images: List<ImageManga> = emptyList(),
    val rank: Long?,
    val rankType: TypeRakingManga?
)


fun GetTopMangas.toMangaDetails(listImages: List<ImageManga>) =
    MangaDetails(
        id = id,
        title = title ?: "Not found",
        images = listImages,
        rank = ranking_value,
        rankType = TypeRakingManga.getType(ranking_type)
    )