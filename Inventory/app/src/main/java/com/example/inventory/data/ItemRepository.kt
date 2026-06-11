package com.example.inventory.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
): ItemRepositoryInterface {

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.geAllItem()

    override fun getItemStream(id: Int): Flow<Item> = itemDao.getItem(id)

    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    override suspend fun updateItem(item: Item) = itemDao.update(item)
}