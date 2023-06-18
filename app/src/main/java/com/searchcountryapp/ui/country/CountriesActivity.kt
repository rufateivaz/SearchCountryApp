package com.searchcountryapp.ui.country

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.searchcountryapp.databinding.ActivityCountriesBinding
import com.searchcountryapp.domain.model.Country
import com.searchcountryapp.ui.country.adapter.CountryAdapter
import com.searchcountryapp.ui.country.injection.injectUIFeatures


class CountriesActivity : AppCompatActivity() {

    init {
        injectUIFeatures()
    }

    private lateinit var binding: ActivityCountriesBinding

    private val viewModel: CountriesViewModel by viewModels()

    private val countryAdapter: CountryAdapter by lazy {
        CountryAdapter(::onItemClicked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = countryAdapter

        viewModel.countries.observe(this) {
            countryAdapter.saveData(it)
        }

        startTextChangeListener()
    }

    private fun startTextChangeListener() {
        binding.searchContent.doAfterTextChanged {
            val content = binding.searchContent.text.toString()
            viewModel.getFlowOfCountries(content)
        }
    }

    private fun onItemClicked(country: Country) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(country.mapUrl)))
    }
}