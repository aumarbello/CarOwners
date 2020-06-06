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
        private val binding = ItemOwnerBinding.bind(view)

        fun bindItem(item: CarOwner) {
            val res = itemView.resources

            binding.name.text = res.getString(R.string.format_full_name, item.firstName, item.lastName)
            binding.job.text = item.jobTitle
            binding.gender.text = item.gender
            binding.email.text = item.email
            binding.country.text = item.country
            binding.car.text = res.getString(R.string.format_car, item.carYear, item.carModel, item.carColor)
            binding.biography.text = item.biography
        }
    }
}