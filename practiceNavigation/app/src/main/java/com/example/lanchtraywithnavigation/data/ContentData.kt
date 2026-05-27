package com.example.lanchtraywithnavigation.data

import androidx.annotation.StringRes

data class ContentData(
    @StringRes val title: Int,
    @StringRes val description:Int,
    val price: Double,
)
