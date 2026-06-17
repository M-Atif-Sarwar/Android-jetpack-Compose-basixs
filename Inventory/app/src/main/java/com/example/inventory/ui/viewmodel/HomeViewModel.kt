package com.example.inventory.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUIState(val itemList:List<Item> = listOf())

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ItemRepository
): ViewModel() {

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
    val homeUIState: StateFlow<HomeUIState> = repository.getAllItemsStream()
        .map { HomeUIState(it)}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )
}