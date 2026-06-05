package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
by default entity use data class as
 as table name
 */

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name:String,
    val price:Double,
    val quantity: Int
)



