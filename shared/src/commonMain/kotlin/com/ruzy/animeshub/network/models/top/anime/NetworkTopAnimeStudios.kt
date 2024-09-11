package com.ruzy.animeshub.network.models.top.anime

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopAnimeStudios (

  @SerialName("mal_id" ) var malId : Int?    = null,
  @SerialName("type"   ) var type  : String? = null,
  @SerialName("name"   ) var name  : String? = null,
  @SerialName("url"    ) var url   : String? = null

)