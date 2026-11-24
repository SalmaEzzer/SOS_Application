package com.supmti.sos_application.features.emergency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FireTruck
import androidx.compose.material.icons.outlined.LocalPolice
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.supmti.sos_application.core.ui.components.SOSEmergencyCard
import com.supmti.sos_application.core.ui.components.SOSTopBar
import com.supmti.sos_application.core.ui.theme.SOSColors
import com.supmti.sos_application.core.ui.theme.SOSSpacing
import com.supmti.sos_application.core.ui.theme.SOSTheme

@Composable
fun EmergencyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding().verticalScroll(rememberScrollState())
            .padding(horizontal = SOSSpacing.ScreenHorizontal, vertical = SOSSpacing.Small),
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Default),
    ) {
        SOSTopBar("Informations d’urgence")
        Surface(color = SOSColors.LightYellow, shape = androidx.compose.material3.MaterialTheme.shapes.large) {
            Text(
                "En cas de danger immédiat, contactez directement les services officiels de votre région. SOS ne fournit pas encore de numéro vérifié.",
                modifier = Modifier.padding(SOSSpacing.Default),
                color = SOSColors.TextPrimary,
            )
        }
        Text("Aucun appel n’est possible tant qu’un numéro officiel n’a pas été vérifié.", color = SOSColors.TextSecondary)
        SOSEmergencyCard("Police secours", Icons.Outlined.LocalPolice, modifier = Modifier.fillMaxWidth())
        SOSEmergencyCard("Ambulance / SAMU", Icons.Outlined.MedicalServices, modifier = Modifier.fillMaxWidth())
        SOSEmergencyCard("Pompiers", Icons.Outlined.FireTruck, modifier = Modifier.fillMaxWidth())
        SOSEmergencyCard("Gendarmerie", Icons.Outlined.Shield, modifier = Modifier.fillMaxWidth())
        SOSEmergencyCard("Protection civile", Icons.Outlined.SupportAgent, modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable private fun EmergencyScreenPreview() = SOSTheme { EmergencyScreen() }
