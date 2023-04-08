package com.example.changeablerecycler.utils

import com.example.changeablerecycler.data.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class ItemsManager {

    private val _listItemsToShow = MutableStateFlow(LinkedList<Item>())
    val listItemsToShow: StateFlow<LinkedList<Item>> = _listItemsToShow

    private val listItemsDeleted = LinkedList<Item>()

    private var itemNumber = 0
    private var isToContinue = true

    private val initialItemsCount = 15
    private val delayBetweenItemsCreation = 5000L

    init {
        initialFillListItemsToShow()
    }

    private fun initialFillListItemsToShow() {
        val listItemsToShowUpdated = LinkedList<Item>()
        listItemsToShowUpdated.addAll(_listItemsToShow.value)
        repeat(initialItemsCount) {
            itemNumber += 1
            listItemsToShowUpdated.add(Item(number = itemNumber))
        }
        _listItemsToShow.value = listItemsToShowUpdated
    }

    suspend fun startPlay() {
        isToContinue = true
        while (isToContinue) {
            delay(delayBetweenItemsCreation)
            val itemFromDeleted = listItemsDeleted.firstOrNull()
            val indexRandom =
                if (_listItemsToShow.value.isEmpty()) 0 else Random.nextInt(0.._listItemsToShow.value.size)
            val itemToAdd = when {
                itemFromDeleted != null -> itemFromDeleted
                else -> {
                    itemNumber += 1
                    Item(number = itemNumber)
                }
            }
            val listItemsToShowUpdated = LinkedList<Item>()
            listItemsToShowUpdated.addAll(_listItemsToShow.value)
            listItemsToShowUpdated.add(indexRandom, itemToAdd)
            _listItemsToShow.value = listItemsToShowUpdated
        }
    }

    fun stopPlay() {
        isToContinue = false
    }
}