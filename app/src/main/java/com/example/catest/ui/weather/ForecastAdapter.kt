package com.example.catest.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catest.databinding.ItemForecastDayBinding
import com.example.catest.ui.common.extensions.loadUrl
import com.example.catest.ui.common.extensions.setCompleteDate
import com.example.catest.ui.common.extensions.setTemps
import com.example.domain.ForecastInfo
import java.util.*

class ForecastAdapter: ListAdapter<ForecastInfo, ForecastAdapter.ForecastViewHolder>(UI_MODEL_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_ITEM -> ForecastViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        when(holder){
            is ForecastViewHolder -> {
                val item  = getItem(position) as ForecastInfo
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return ITEM_VIEW_TYPE_ITEM
    }

    class ForecastViewHolder(private val binding: ItemForecastDayBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(forecast: ForecastInfo) {
                with(binding){
                    forecast.dt?.let {
                        itemDate.setCompleteDate(it)
                    }
                    forecast.main?.let{
                        itemTemp.setTemps(it.tempMin, it.tempMax)
                    }
                    itemWeatherType.text = forecast.weather?.first()?.description
                    forecast.weather?.first()?.icon?.let {
                        itemImage.loadUrl(forecast.weather?.first()?.icon)
                    }
                }
            }

        companion object {
            fun from(parent: ViewGroup): ForecastViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemForecastDayBinding.inflate(layoutInflater, parent, false)
                return ForecastViewHolder(binding)
            }
        }
        }

    companion object {
        private const val ITEM_VIEW_TYPE_ITEM = -1
        private val UI_MODEL_COMPARATOR = object : DiffUtil.ItemCallback<ForecastInfo>() {
            override fun areItemsTheSame(oldItem: ForecastInfo, newItem: ForecastInfo): Boolean {
                return oldItem.dt == newItem.dt
            }

            override fun areContentsTheSame(oldItem: ForecastInfo, newItem: ForecastInfo): Boolean = false
        }
    }
}