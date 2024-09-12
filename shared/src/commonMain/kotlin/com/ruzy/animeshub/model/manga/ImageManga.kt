package com.ruzy.animeshub.model.manga

import com.ruzy.animeshub.db.manga.ImageMangaEntity

data class ImageManga(
    val id: Long,
    val url: String,
    val type: String = ""
)

fun ImageMangaEntity.toExternalModel() = ImageManga(
    id = id,
    url = url ?: ""
)