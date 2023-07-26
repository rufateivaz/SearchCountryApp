package com.searchcountryapp.ui.country

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import com.searchcountryapp.ui.theme.CountriesApplicationTheme
import com.sample.domain.model.Country
import com.searchcountryapp.ui.compose.CountryListView

class CountriesActivity : AppCompatActivity() {

    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesApplicationTheme {
                Surface {
                    CountryListView(
                        vm = viewModel,
                        onItemClicked = ::onItemClicked
                    )
                }
            }
        }
    }

    private fun onItemClicked(country: Country) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(country.mapUrl)))
    }
}
