package com.ruzy.animeshub.model.anime

import com.ruzy.animeshub.db.anime.ImageAnimeEntity

data class ImageAnime(
    val id: Long,
    val url: String,
    val type: String = ""
)

fun ImageAnimeEntity.toExternalModel() = ImageAnime(
    id = id,
    url = url ?: ""
)