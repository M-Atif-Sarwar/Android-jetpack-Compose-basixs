package com.example.courselist.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TopicData(
    @DrawableRes val courseImage:Int,
    @StringRes val title:Int,
    val numberOfSeats:Int,
)
