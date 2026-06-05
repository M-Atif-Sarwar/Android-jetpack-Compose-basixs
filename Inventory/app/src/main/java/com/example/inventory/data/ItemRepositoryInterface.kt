package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

interface ItemRepositoryInterface {

    //get all item
    suspend fun getAllItemStream(): Flow<List<Item>>

//    get single item
    suspend fun getItemStream(item:Int): Flow<Item>

    //insert item
    suspend fun insertItem(item:Item)

    //update item
    suspend fun updateItem(item:Item)

    // delete item
    suspend fun deleteItem(item:Item)

}