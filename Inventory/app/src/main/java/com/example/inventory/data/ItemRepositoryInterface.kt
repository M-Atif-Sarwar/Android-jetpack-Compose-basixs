package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

interface ItemRepositoryInterface {

    //get all item
    fun getAllItemsStream(): Flow<List<Item>>

//    get single item
    fun getItemStream(id:Int): Flow<Item>

    //insert item
    suspend fun insertItem(item:Item)

    //update item
    suspend fun updateItem(item:Item)

    // delete item
    suspend fun deleteItem(item:Item)

}