package com.searchcountryapp.ui.country

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.searchcountryapp.ui.theme.CountriesApplicationTheme
import com.searchcountryapp.R
import com.searchcountryapp.domain.model.Country
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class CountriesActivity : AppCompatActivity() {

    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesApplicationTheme {
                Surface {
                    CountryList()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CountryList() {
        val list = viewModel.countries.observeAsState(initial = emptyList()).value
        var searchContent by rememberSaveable {
            mutableStateOf("")
        }

        Column {
            TextField(
                value = searchContent,
                onValueChange = {
                    searchContent = it
                    viewModel.getFlowOfCountries(it)
                },
                placeholder = { Text(stringResource(id = R.string.search_countries_hint)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                singleLine = true
            )

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(list) {
                    CountryItemCard(country = it)
                }
            }
        }
    }

    @Composable
    private fun CountryItemCard(country: Country) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onItemClicked(country) },
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.LightGray)
        ) {
            Column {
                Text(
                    text = country.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
                Text(
                    text = stringResource(
                        id = R.string.country_capital,
                        country.capital
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(
                        id = R.string.country_population,
                        country.population
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    AsyncImage(
                        model = country.flagUrl,
                        contentDescription = stringResource(id = R.string.country_flag),
                        modifier = Modifier
                            .width(56.dp)
                            .height(56.dp)
                            .clip(CircleShape),
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    )
                    Column {
                        Text(
                            text = country.region,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = country.subRegion,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
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
