package com.example.changeablerecycler.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.changeablerecycler.adapters.ItemRecyclerViewAdapter
import com.example.changeablerecycler.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()
    private val recyclerViewAdapter: ItemRecyclerViewAdapter by inject()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val spanCount = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 2
            else -> 4
        }

        with(binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, spanCount)
            recyclerView.adapter = recyclerViewAdapter
        }

        lifecycleScope.launch {
            viewModel.listItems.collectLatest { listItems ->
                recyclerViewAdapter.submitList(listItems)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startPlay()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopPlay()
    }
}