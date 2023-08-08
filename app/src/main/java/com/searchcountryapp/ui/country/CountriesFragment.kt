package com.searchcountryapp.ui.country

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.sample.domain.model.Country
import com.searchcountryapp.ui.compose.CountryListView
import com.searchcountryapp.ui.country.injection.injectCountriesModule
import com.searchcountryapp.ui.theme.CountriesApplicationTheme
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CountriesFragment : Fragment() {

    init {
        injectCountriesModule()
    }

    private val viewModel: CountriesViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
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
    }

    private fun onItemClicked(country: Country) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(country.mapUrl)))
    }
}