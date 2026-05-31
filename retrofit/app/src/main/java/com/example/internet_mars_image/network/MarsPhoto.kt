package com.example.internet_mars_image.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id:String,

    @SerialName(value="img_src")
    val imgSrc: String
)
