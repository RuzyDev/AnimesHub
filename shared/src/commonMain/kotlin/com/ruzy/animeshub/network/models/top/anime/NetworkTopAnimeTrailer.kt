package com.ruzy.animeshub.network.models.top.anime

import com.ruzy.animeshub.network.models.top.NetworkTopImages
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetworkTopAnimeTrailer (

  @SerialName("youtube_id" ) var youtubeId : String? = null,
  @SerialName("url"        ) var url       : String? = null,
  @SerialName("embed_url"  ) var embedUrl  : String? = null,
  @SerialName("images"     ) var networkAnimeImagesTop    : NetworkTopImages? = NetworkTopImages()

)