package com.ruzy.animeshub.network.models.top.manga

import com.ruzy.animeshub.network.models.top.NetworkTopItems
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopMangaPagination (

  @SerialName("last_visible_page" ) var lastVisiblePage : Int?     = null,
  @SerialName("has_next_page"     ) var hasNextPage     : Boolean? = null,
  @SerialName("current_page"      ) var currentPage     : Int?     = null,
  @SerialName("items"             ) var items           : NetworkTopItems?   = NetworkTopItems()

)