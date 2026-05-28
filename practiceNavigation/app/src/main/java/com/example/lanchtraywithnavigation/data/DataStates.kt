package com.example.lanchtraywithnavigation.data


data class Summary(
    var title:String ="",
    var price:Double=0.0
)

data class DataStates(
    var subTotal: Double=0.0,
    val tax:Double = 10.0,
    var total: Double=0.0,
    var summary: List<Summary> = emptyList()
)
