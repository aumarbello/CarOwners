package com.aumarbello.carowners.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aumarbello.carowners.R
import com.aumarbello.carowners.databinding.ItemFilterBinding
import com.aumarbello.carowners.models.Filter

class FilterAdapter (
    private val listener: FilterClickListener,
    private val items: List<Filter>
): RecyclerView.Adapter<FilterAdapter.FilterHolder>() {
    interface FilterClickListener {
        fun onFilterSelected(filter: Filter)
    }

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
            val res = itemView.resources
            binding.gender.text = item.gender

            binding.colors.text = if (item.colors.isNotEmpty()) {
                 item.colors.joinToString()
            } else {
                res.getString(R.string.label_none)
            }

            binding.countries.text = if (item.countries.isNotEmpty()) {
                item.countries.joinToString()
            } else {
                res.getString(R.string.label_none)
            }

            itemView.setOnClickListener {
                listener.onFilterSelected(item)
            }
        }
    }
}