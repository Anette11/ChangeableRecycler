package com.example.changeablerecycler.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.changeablerecycler.utils.ItemsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val itemsManager: ItemsManager
) : ViewModel() {

    val listItems = itemsManager.listItemsToShow

    fun startPlay() = viewModelScope.launch(Dispatchers.Default) {
        itemsManager.startPlay()
    }

    fun stopPlay() = viewModelScope.launch(Dispatchers.Default) {
        itemsManager.stopPlay()
    }
}