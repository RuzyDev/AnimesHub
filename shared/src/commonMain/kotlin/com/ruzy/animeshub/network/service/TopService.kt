package com.ruzy.animeshub.network.service

import com.ruzy.animeshub.network.models.top.anime.NetworkTopAnime

interface TopService {
   suspend fun getTopAnime(filter: String): NetworkTopAnime
   suspend fun getTopManga(filter: String): NetworkTopAnime
}
