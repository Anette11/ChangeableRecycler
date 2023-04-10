package com.example.changeablerecycler.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.changeablerecycler.data.Item
import com.example.changeablerecycler.utils.ItemsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    private val itemsManager: ItemsManager
) : ViewModel() {

    val listItems = itemsManager.listItemsToShow

    private var startPlayJob: Job? = null
    private var stopPlayJob: Job? = null

    fun startPlay() {
        startPlayJob?.cancel()
        startPlayJob = viewModelScope.launch(Dispatchers.Default) {
            itemsManager.startPlay()
        }
    }

    fun stopPlay() {
        stopPlayJob?.cancel()
        stopPlayJob = viewModelScope.launch(Dispatchers.Default) {
            itemsManager.stopPlay()
        }
    }

    fun deleteItem(item: Item) = viewModelScope.launch(Dispatchers.Default) {
        itemsManager.deleteItem(item = item)
    }
}