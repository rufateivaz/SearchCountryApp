package com.searchcountryapp.ui.country.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.searchcountryapp.R
import com.searchcountryapp.domain.model.Country

class CountryAdapter(
    private val onClick: (country: Country) -> Unit
) : ListAdapter<Country, CountryAdapter.ViewHolder>(CountryDiffUtil) {

    companion object CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.country_item, viewGroup, false)

        return ViewHolder(view) {
            onClick(currentList[it])
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    class ViewHolder(
        itemView: View,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView
        private val capital: TextView
        private val population: TextView
        private val region: TextView
        private val subRegion: TextView
        private val flagImage: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            capital = itemView.findViewById(R.id.capital)
            population = itemView.findViewById(R.id.population)
            region = itemView.findViewById(R.id.region)
            subRegion = itemView.findViewById(R.id.subRegion)
            flagImage = itemView.findViewById(R.id.flagImage)
            itemView.setOnClickListener { onItemClicked(adapterPosition) }
        }

        fun bind(country: Country) {
            name.text = country.name
            capital.text = itemView.context.getString(
                R.string.country_capital,
                country.capital
            )
            population.text = itemView.context.getString(
                R.string.country_population,
                country.population
            )
            region.text = country.region
            subRegion.text = country.subRegion

            Glide
                .with(itemView.context)
                .load(country.flagUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(flagImage)
        }
    }

    fun saveData(countries: List<Country>) {
        submitList(countries)
    }
}