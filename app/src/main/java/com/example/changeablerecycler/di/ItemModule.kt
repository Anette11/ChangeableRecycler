package com.example.changeablerecycler.di

import com.example.changeablerecycler.adapters.ItemRecyclerViewAdapter
import com.example.changeablerecycler.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)

    factory { ItemRecyclerViewAdapter() }
}