package com.supmti.sos_application.features.resources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Accessible
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.supmti.sos_application.core.model.demoResourceExample
import com.supmti.sos_application.core.ui.components.SOSCategoryCard
import com.supmti.sos_application.core.ui.components.SOSFilterChip
import com.supmti.sos_application.core.ui.components.SOSErrorState
import com.supmti.sos_application.core.ui.components.SOSEmptyState
import com.supmti.sos_application.core.ui.components.SOSEmergencyButton
import com.supmti.sos_application.core.ui.components.SOSEmergencyCard
import com.supmti.sos_application.core.ui.components.SOSIconButton
import com.supmti.sos_application.core.ui.components.SOSLoadingState
import com.supmti.sos_application.core.ui.components.SOSPrimaryButton
import com.supmti.sos_application.core.ui.components.SOSResourceCard
import com.supmti.sos_application.core.ui.components.SOSSearchBar
import com.supmti.sos_application.core.ui.components.SOSSecondaryButton
import com.supmti.sos_application.core.ui.components.SOSSectionHeader
import com.supmti.sos_application.core.ui.components.SOSStatusBadge
import com.supmti.sos_application.core.ui.components.SOSTopBar
import com.supmti.sos_application.core.ui.components.SOSVerifiedBadge
import com.supmti.sos_application.core.ui.theme.SOSColors
import com.supmti.sos_application.core.ui.theme.SOSSpacing
import com.supmti.sos_application.core.ui.theme.SOSTheme

@Composable
fun ComponentGalleryScreen(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var chipSelected by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(SOSSpacing.ScreenHorizontal),
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Default),
    ) {
        SOSTopBar("Catalogue des composants SOS")
        Text("Démonstration interne — aucun service ni numéro réel n’est présenté.", color = SOSColors.TextSecondary)
        SOSSectionHeader("Actions")
        Row(horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
            SOSPrimaryButton("Principal", {}, modifier = Modifier.weight(1f))
            SOSSecondaryButton("Secondaire", {}, modifier = Modifier.weight(1f), icon = Icons.Outlined.Navigation)
        }
        SOSEmergencyButton("Appel désactivé", {}, modifier = Modifier.fillMaxWidth(), enabled = false)
        SOSIconButton({}, Icons.Outlined.Tune, "Filtres")

        SOSSectionHeader("Recherche et filtres")
        SOSSearchBar(query, { query = it }, onFilterClick = {})
        Row(horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
            SOSFilterChip("Santé", chipSelected, { chipSelected = !chipSelected })
            SOSFilterChip("Social", false, {})
        }

        SOSSectionHeader("Cartes")
        SOSCategoryCard("Handicap", Icons.AutoMirrored.Outlined.Accessible, {}, Modifier.fillMaxWidth())
        SOSResourceCard(demoResourceExample, Icons.Outlined.LocalHospital)
        SOSEmergencyCard("Police secours", Icons.Outlined.AddAlert)

        SOSSectionHeader("Badges")
        Row(horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
            SOSStatusBadge("Démonstration", background = SOSColors.LightYellow, foreground = SOSColors.TextPrimary)
            SOSVerifiedBadge(verified = false)
        }
        SOSSectionHeader("États d’écran")
        SOSEmptyState("Aucun élément", "Un état vide explicite.", icon = Icons.Outlined.FavoriteBorder)
        SOSLoadingState("Chargement de démonstration…")
        SOSErrorState("État d’erreur de démonstration.", {})
        SOSSectionHeader("Section avec action", actionLabel = "Voir tout", onAction = {})
        SpacerForGallery()
    }
}

@Composable
private fun SpacerForGallery() {
    androidx.compose.foundation.layout.Spacer(Modifier.padding(bottom = SOSSpacing.Large))
}

@Preview(showBackground = true)
@Composable private fun ComponentGalleryScreenPreview() = SOSTheme { ComponentGalleryScreen() }
