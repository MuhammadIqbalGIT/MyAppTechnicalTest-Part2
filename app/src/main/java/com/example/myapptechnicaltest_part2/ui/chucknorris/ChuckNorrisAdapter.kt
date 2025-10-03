package com.example.myapptechnicaltest_part2.ui.chucknorris

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.core.data.source.remote.response.JokeItem
import com.example.myapptechnicaltest_part2.databinding.ItemChucknorrisJokeBinding

class ChuckNorrisAdapter :
    ListAdapter<JokeItem, ChuckNorrisAdapter.JokeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChucknorrisJokeBinding.inflate(inflater, parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class JokeViewHolder(private val binding: ItemChucknorrisJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: JokeItem) {
            with(binding) {
                tvJokeText.text = item.value

                imgChuck.load(item.iconUrl) {
                    crossfade(true)
                }

                tvCategory.text = if (item.categories.isNotEmpty()) {
                    item.categories.joinToString(", ")
                } else {
                    "Uncategorized"
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<JokeItem>() {
    override fun areItemsTheSame(oldItem: JokeItem, newItem: JokeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: JokeItem, newItem: JokeItem): Boolean {
        return oldItem == newItem
    }
}
