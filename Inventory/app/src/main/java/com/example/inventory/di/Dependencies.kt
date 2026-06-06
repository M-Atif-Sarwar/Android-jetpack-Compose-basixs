package com.example.inventory.di

import android.content.Context
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.ItemDao
import com.example.inventory.data.ItemRepository
import com.example.inventory.data.ItemRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InventoryDependencies{

    @Provides
    @Singleton
    fun getDatabase(
        @ApplicationContext context: Context
    ): ItemDao{
        return InventoryDatabase.getDatabase(context).itemDoa()
    }

    @Provides
    @Singleton
    fun getRepository(itemDoa: ItemDao): ItemRepositoryInterface{
        ItemRepository(itemDoa)
    }
}