package com.aumarbello.carowners.ui.owners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aumarbello.carowners.R
import com.aumarbello.carowners.databinding.ItemOwnerBinding
import com.aumarbello.carowners.models.CarOwner

class CarOwnersAdapter (private val items: List<CarOwner>): RecyclerView.Adapter<CarOwnersAdapter.CarOwnersHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarOwnersHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_owner, parent, false)

        return CarOwnersHolder(view)
    }

    override fun onBindViewHolder(holder: CarOwnersHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size

    inner class CarOwnersHolder(view: View): RecyclerView.ViewHolder(view) {
        private val bind = ItemOwnerBinding.bind(view)

        fun bindItem(item: CarOwner) {
            bind.gender.text = item.firstName
            bind.colors.text = item.carModel
            bind.countries.text = item.country
        }
    }
}