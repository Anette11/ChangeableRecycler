package com.example.changeablerecycler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.changeablerecycler.data.Item
import com.example.changeablerecycler.databinding.ItemElementBinding

class ItemRecyclerViewAdapter : RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder>() {

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val item = asyncListDiffer.currentList[position]
        with(holder.binding) {
            textViewNumber.text = "${item.number}"
            imageViewDelete.setOnClickListener {
                onItemClick?.invoke(item)
            }
            cardView.setScaleAnimation()
        }
    }

    class ItemViewHolder(
        val binding: ItemElementBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean = oldItem.number == newItem.number

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean = oldItem == newItem
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtilItemCallback)

    fun submitList(
        listItems: List<Item>
    ) = asyncListDiffer.submitList(listItems)

    var onItemClick: ((Item) -> Unit)? = null

    private fun View.setScaleAnimation() =
        this.startAnimation(
            ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            ).apply {
                duration = 500L
            }
        )
}