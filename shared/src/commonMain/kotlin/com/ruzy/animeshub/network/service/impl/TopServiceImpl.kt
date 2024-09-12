package com.ruzy.animeshub.network.service.impl

import com.ruzy.animeshub.network.models.top.anime.NetworkTopAnime
import com.ruzy.animeshub.network.service.TopService
import com.ruzy.animeshub.network.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TopServiceImpl(
    private val api: HttpClient
) : TopService {

    override suspend fun getTopAnime(filter: String): NetworkTopAnime =
        safeApiCall {
            api.get(urlString = "top/anime"){
                parameter("filter", filter)
                contentType(ContentType.Application.Json)
            }.body()
        }

    override suspend fun getTopManga(filter: String): NetworkTopAnime =
        safeApiCall {
            api.get(urlString = "top/manga"){
                parameter("filter", filter)
                contentType(ContentType.Application.Json)
            }.body()
        }
}