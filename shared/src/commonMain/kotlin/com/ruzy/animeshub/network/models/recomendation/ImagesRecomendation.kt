package com.ruzy.animeshub.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImagesRecomendation (

  @SerialName("jpg"  ) var jpgRecomendation  : JpgRecomendation?  = JpgRecomendation(),
  @SerialName("webp" ) var webp : Webp? = Webp()

)