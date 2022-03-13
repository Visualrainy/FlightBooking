package com.tw.booking.profile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tw.booking.R
import com.tw.booking.databinding.ConsumptionsItemBinding
import com.tw.booking.profile.model.Consumption

class ConsumptionsAdapter(val consumptions: List<Consumption>?) :
    RecyclerView.Adapter<ConsumptionsAdapter.ConsumptionsViewHolder>() {
    override fun getItemCount(): Int {
        return consumptions?.size ?: 0
    }

    override fun onBindViewHolder(holder: ConsumptionsViewHolder, position: Int) {
        holder.bind(consumptions?.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsumptionsViewHolder {
        val binding = DataBindingUtil.inflate<ConsumptionsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.consumptions_item,
            parent,
            false
        )
        return ConsumptionsViewHolder(binding)
    }

    class ConsumptionsViewHolder(val binding: ConsumptionsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(consumption: Consumption?) {
            consumption?.let {
                binding.consumption = consumption
            }
        }
    }
}