package com.supmti.sos_application.features.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supmti.sos_application.core.ui.components.SOSSearchBar
import com.supmti.sos_application.core.ui.components.SOSTopBar
import com.supmti.sos_application.core.ui.theme.SOSColors
import com.supmti.sos_application.core.ui.theme.SOSSpacing
import com.supmti.sos_application.core.ui.theme.SOSTheme

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding().padding(horizontal = SOSSpacing.ScreenHorizontal, vertical = SOSSpacing.Small),
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Default),
    ) {
        SOSTopBar("Aide autour de vous")
        SOSSearchBar("", {}, placeholder = "Rechercher dans cette zone…")
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f).background(SOSColors.LightGreen, RoundedCornerShape(28.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
                Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = SOSColors.Primary)
                Text("Carte non connectée", style = MaterialTheme.typography.titleMedium)
                Text("Aucun lieu ou position n’est affiché.", color = SOSColors.TextSecondary)
            }
        }
        Text("Carte illustrative — aucune donnée de démonstration géographique.", modifier = Modifier.align(Alignment.CenterHorizontally), color = SOSColors.TextSecondary)
    }
}

@Preview(showBackground = true)
@Composable private fun MapScreenPreview() = SOSTheme { MapScreen() }
