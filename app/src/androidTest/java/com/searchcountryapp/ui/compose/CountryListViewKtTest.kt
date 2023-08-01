package com.searchcountryapp.ui.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.sample.domain.model.Country
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CountryListViewKtTest {

    @get: Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            MaterialTheme {
                CountryItemCard(country = country, onItemClicked = {})
            }
        }
    }

    @Test
    fun givenCountryItem_TheCorrespondingCountryInfoShouldBeDisplayedOnTheItemCard() {
        composeRule.onNodeWithText("United States").assertIsDisplayed()
        composeRule.onNodeWithText("Capital: Washington, D.C.").assertIsDisplayed()
        composeRule.onNodeWithText("Population: 329484123").assertIsDisplayed()
        composeRule.onNodeWithText("Americas").assertIsDisplayed()
        composeRule.onNodeWithText("North America").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Country flag").assertIsDisplayed()
        composeRule.onNodeWithTag("CountryItemCard").assertHasClickAction()
    }

    private val country = Country(
        "United States",
        329484123L,
        "Washington, D.C.",
        "FlagUrl",
        "MapUrl",
        "Americas",
        "North America"
    )
}