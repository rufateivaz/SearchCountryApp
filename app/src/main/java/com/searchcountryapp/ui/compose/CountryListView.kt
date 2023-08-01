package com.searchcountryapp.ui.compose

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sample.domain.model.Country
import com.searchcountryapp.R
import com.searchcountryapp.ui.country.CountriesViewModel

/**
 * Country List view composable method.
 * */
@Composable
fun CountryListView(
    vm: CountriesViewModel,
    onItemClicked: (Country) -> Unit
) {
    val list = vm.countries.observeAsState(initial = emptyList()).value
    var searchContent by rememberSaveable { mutableStateOf("") }

    Column {
        TextField(
            value = searchContent,
            onValueChange = {
                searchContent = it
                vm.getFlowOfCountries(it)
            },
            placeholder = { Text(stringResource(id = R.string.search_countries_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            singleLine = true
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(list) {
                CountryItemCard(country = it, onItemClicked)
            }
        }
    }
}

@Composable
fun CountryItemCard(
    country: Country,
    onItemClicked: (Country) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClicked(country) }
            .testTag("CountryItemCard"),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {
        Column {
            CountryInfoText(text = country.name, modifier = Modifier, fontSize = 24.sp)

            val capitalText = stringResource(id = R.string.country_capital, country.capital)
            CountryInfoText(text = capitalText, modifier = Modifier)

            val populationText =
                stringResource(id = R.string.country_population, country.population)
            CountryInfoText(text = populationText, modifier = Modifier)

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
                        .height(56.dp),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                )
                Column {
                    CountryInfoText(
                        text = country.region,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp),
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.ExtraBold
                    )
                    CountryInfoText(
                        text = country.subRegion,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp),
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Composable
fun CountryInfoText(
    text: String,
    modifier: Modifier,
    fontFamily: FontFamily? = null,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.SemiBold,
    fontSize: TextUnit = 18.sp
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        modifier = modifier.fillMaxWidth(),
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}