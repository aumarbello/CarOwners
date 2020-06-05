package com.aumarbello.carowners.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.aumarbello.carowners.R
import com.aumarbello.carowners.databinding.ItemFilterBinding
import com.aumarbello.carowners.models.Filter

class FilterAdapter (private val items: List<Filter>): RecyclerView.Adapter<FilterAdapter.FilterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_filter, parent, false)

        return FilterHolder(view)
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size

    inner class FilterHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemFilterBinding.bind(view)

        fun bindItem(item: Filter) {
            binding.gender.text = item.gender

            binding.colors.isVisible = item.colors.isNotEmpty()
            if (item.colors.isNotEmpty()) {
                binding.colors.text = item.colors.joinToString()
            }

            binding.countries.isVisible = item.countries.isNotEmpty()
            if (item.countries.isNotEmpty()) {
                binding.countries.text = item.countries.joinToString()
            }
        }
    }
}