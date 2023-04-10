package com.example.changeablerecycler.utils

import com.example.changeablerecycler.data.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class ItemsManager {

    private val _listItemsToShow = MutableStateFlow(emptyList<Item>())
    val listItemsToShow: StateFlow<List<Item>> = _listItemsToShow

    private val listItemsDeleted = mutableListOf<Item>()

    private var itemNumber = 0
    private var isToContinue = true

    private val initialItemsCount = 15
    private val delayBetweenItemsCreation = 5000L

    init {
        initialFillListItemsToShow()
    }

    private fun initialFillListItemsToShow() {
        _listItemsToShow.value = LinkedList<Item>().apply {
            addAll(_listItemsToShow.value)
            repeat(initialItemsCount) {
                itemNumber += 1
                add(Item(number = itemNumber))
            }
        }
    }

    suspend fun startPlay() {
        isToContinue = true
        while (isToContinue) {
            delay(delayBetweenItemsCreation)
            val itemToAdd = findItemToAdd()
            val indexRandom =
                if (_listItemsToShow.value.isEmpty()) 0 else Random.nextInt(0.._listItemsToShow.value.size)
            _listItemsToShow.value = LinkedList<Item>().apply {
                addAll(_listItemsToShow.value)
                add(indexRandom, itemToAdd)
            }
        }
    }

    private fun findItemToAdd(): Item {
        val itemFromListItemsDeleted = listItemsDeleted.toList().shuffled().firstOrNull()
        return itemFromListItemsDeleted?.let {
            listItemsDeleted.remove(itemFromListItemsDeleted)
            itemFromListItemsDeleted
        } ?: kotlin.run {
            itemNumber += 1
            Item(number = itemNumber)
        }
    }

    fun stopPlay() {
        isToContinue = false
    }

    fun deleteItem(
        item: Item
    ) {
        listItemsDeleted.add(item)
        _listItemsToShow.value = LinkedList<Item>().apply {
            addAll(_listItemsToShow.value)
            remove(item)
        }
    }
}