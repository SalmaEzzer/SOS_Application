package com.supmti.sos_application.features.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.supmti.sos_application.core.ui.components.SOSEmptyState
import com.supmti.sos_application.core.ui.components.SOSFilterChip
import com.supmti.sos_application.core.ui.components.SOSSearchBar
import com.supmti.sos_application.core.ui.components.SOSSectionHeader
import com.supmti.sos_application.core.ui.components.SOSTopBar
import com.supmti.sos_application.core.ui.theme.SOSSpacing
import com.supmti.sos_application.core.ui.theme.SOSTheme

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf("Tous") }
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding().padding(horizontal = SOSSpacing.ScreenHorizontal, vertical = SOSSpacing.Small),
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Default),
    ) {
        SOSTopBar("Trouver de l’aide")
        SOSSearchBar(query, { query = it }, placeholder = "Rechercher un service…", onFilterClick = {})
        androidx.compose.foundation.layout.Row(horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
            listOf("Tous", "Santé", "Social", "Juridique").forEach { filter ->
                SOSFilterChip(filter, selected == filter, { selected = filter })
            }
        }
        SOSSectionHeader("Résultats")
        SOSEmptyState(
            title = "Aucun résultat connecté",
            message = "La recherche de ressources sera disponible quand une source approuvée sera configurée.",
            icon = Icons.Outlined.Search,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Text("Aucune donnée réelle n’est fournie dans cette version de démonstration.")
    }
}

@Preview(showBackground = true)
@Composable private fun SearchScreenPreview() = SOSTheme { SearchScreen() }
