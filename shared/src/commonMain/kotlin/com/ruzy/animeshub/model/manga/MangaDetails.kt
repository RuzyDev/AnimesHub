package com.ruzy.animeshub.model.manga

import com.ruzy.animeshub.db.MangaEntity

data class MangaDetails(
    val id: Long,
    val title: String,
    val images: List<ImageManga> = emptyList()
)


fun MangaEntity.toMangaDetails(listImages: List<ImageManga>) =
    MangaDetails(
        id = id,
        title = title ?: "Not found",
        images = listImages
    )