package com.example.changeablerecycler.ui

import androidx.lifecycle.ViewModel
import com.example.changeablerecycler.data.Item

class MainViewModel : ViewModel() {

    val list = mutableListOf<Item>()

    init {
        (1..15).forEach { number ->
            list.add(Item(number = number))
        }
    }
}