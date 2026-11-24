package com.supmti.sos_application.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.automirrored.outlined.Accessible
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.Balance
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.PhoneInTalk
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supmti.sos_application.core.model.DemoResource
import com.supmti.sos_application.core.ui.components.SOSCategoryCard
import com.supmti.sos_application.core.ui.components.SOSCitySelector
import com.supmti.sos_application.core.ui.components.SOSEmergencyButton
import com.supmti.sos_application.core.ui.components.SOSIconButton
import com.supmti.sos_application.core.ui.components.SOSResourceCard
import com.supmti.sos_application.core.ui.components.SOSSearchBar
import com.supmti.sos_application.core.ui.components.SOSSectionHeader
import com.supmti.sos_application.core.ui.theme.SOSColors
import com.supmti.sos_application.core.ui.theme.SOSSpacing
import com.supmti.sos_application.core.ui.theme.SOSTheme

private data class HomeCategory(
    val title: String,
    val icon: ImageVector,
    val background: Color,
    val iconColor: Color,
)

private val homeCategories = listOf(
    HomeCategory("Urgences", Icons.Outlined.NotificationsActive, SOSColors.LightRed, SOSColors.Emergency),
    HomeCategory("Santé", Icons.Outlined.MedicalServices, SOSColors.LightBlue, SOSColors.Primary),
    HomeCategory("Femmes & violence", Icons.Outlined.VolunteerActivism, SOSColors.LightPurple, Color(0xFF8057B5)),
    HomeCategory("Enfants & familles", Icons.Outlined.ChildCare, SOSColors.LightYellow, Color(0xFFAD6900)),
    HomeCategory("Santé mentale", Icons.Outlined.Psychology, SOSColors.LightGreen, SOSColors.Success),
    HomeCategory("Handicap", Icons.AutoMirrored.Outlined.Accessible, SOSColors.LightBlue, SOSColors.Primary),
    HomeCategory("Aide sociale", Icons.Outlined.Handshake, SOSColors.LightGreen, SOSColors.Success),
    HomeCategory("Aide juridique", Icons.Outlined.Balance, SOSColors.LightPurple, Color(0xFF8057B5)),
    HomeCategory("Services publics", Icons.Outlined.AccountBalance, SOSColors.LightYellow, Color(0xFFAD6900)),
    HomeCategory("Animaux", Icons.Outlined.Pets, SOSColors.LightYellow, Color(0xFFAD6900)),
)

private val demoResources = listOf(
    DemoResource(
        name = "Exemple de centre de santé — fictif",
        category = "Santé (démonstration)",
        description = "Carte de démonstration uniquement. Ce lieu n’est pas une ressource réelle et ses informations ne sont pas vérifiées.",
    ) to Icons.Outlined.LocalHospital,
    DemoResource(
        name = "Exemple d’association — fictif",
        category = "Aide sociale (démonstration)",
        description = "Contenu fictif pour présenter le composant. Aucun service, horaire ou disponibilité n’est annoncé.",
    ) to Icons.Outlined.Handshake,
)

@Composable
fun HomeScreen(
    onSearch: () -> Unit,
    onEmergency: () -> Unit,
    onOpenComponents: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    var query by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = SOSSpacing.ScreenHorizontal, vertical = SOSSpacing.Small),
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Default),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().widthIn(max = 560.dp),
            verticalArrangement = Arrangement.spacedBy(SOSSpacing.Default),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Surface(color = SOSColors.Primary, shape = CircleShape, modifier = Modifier.size(42.dp)) {
                    Icon(Icons.Outlined.MonitorHeart, contentDescription = "SOS", tint = Color.White, modifier = Modifier.padding(10.dp))
                }
                Text("SOS", modifier = Modifier.padding(start = SOSSpacing.Small).weight(1f), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                if (onOpenComponents != null) {
                    SOSIconButton(onOpenComponents, Icons.Outlined.Widgets, "Catalogue des composants")
                }
            }

            SOSCitySelector(cityName = "Témara")
            Column(verticalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
                Text("Comment pouvons-nous vous aider ?", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("Trouvez les informations adaptées à votre situation.", style = MaterialTheme.typography.bodyLarge, color = SOSColors.TextSecondary)
            }
            SOSSearchBar(
                value = query,
                onValueChange = { query = it },
                placeholder = "De quelle aide avez-vous besoin ?",
                onFilterClick = onSearch,
            )

            Surface(
                color = SOSColors.LightRed,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth(),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFC7C9)),
            ) {
                Column(Modifier.padding(SOSSpacing.Default), verticalArrangement = Arrangement.spacedBy(SOSSpacing.Medium)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Medium)) {
                        Icon(Icons.Outlined.PhoneInTalk, contentDescription = null, tint = SOSColors.Emergency, modifier = Modifier.size(28.dp))
                        Column {
                            Text("Besoin d’aide urgente ?", fontWeight = FontWeight.Bold)
                            Text("Consultez les informations disponibles.", color = SOSColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    SOSEmergencyButton(
                        text = "Voir les informations d’urgence",
                        onClick = onEmergency,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            SOSSectionHeader("Explorez par catégorie")
            Column(verticalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
                homeCategories.chunked(2).forEach { rowCategories ->
                    Row(horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
                        rowCategories.forEach { category ->
                            SOSCategoryCard(
                                title = category.title,
                                icon = category.icon,
                                onClick = onSearch,
                                modifier = Modifier.weight(1f),
                                containerColor = category.background,
                                iconTint = category.iconColor,
                            )
                        }
                        if (rowCategories.size == 1) Spacer(Modifier.weight(1f))
                    }
                }
            }

            SOSSectionHeader("Aide près de vous", actionLabel = "Rechercher", onAction = onSearch)
            Surface(
                color = SOSColors.LightYellow,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    "Exemples fictifs pour démonstration — aucune ressource réelle ou vérifiée n’est affichée.",
                    modifier = Modifier.padding(SOSSpacing.Medium),
                    color = SOSColors.TextPrimary,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            demoResources.forEach { (resource, icon) -> SOSResourceCard(resource, icon) }
            Spacer(Modifier.size(SOSSpacing.Medium))
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HomeScreenPreview() {
    SOSTheme {
        HomeScreen(onSearch = {}, onEmergency = {}, onOpenComponents = {})
    }
}
