package com.supmti.sos_application.features.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.supmti.sos_application.core.ui.components.SOSEmptyState
import com.supmti.sos_application.core.ui.components.SOSTopBar
import com.supmti.sos_application.core.ui.theme.SOSSpacing
import com.supmti.sos_application.core.ui.theme.SOSTheme

@Composable
fun FavoritesScreen(onFindHelp: () -> Unit = {}, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding().padding(horizontal = SOSSpacing.ScreenHorizontal, vertical = SOSSpacing.Small),
    ) {
        SOSTopBar("Mes favoris")
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SOSEmptyState(
                title = "Aucun favori pour le moment",
                message = "Enregistrez des ressources approuvées pour les retrouver rapidement.",
                actionLabel = "Trouver de l’aide",
                onAction = onFindHelp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable private fun FavoritesScreenPreview() = SOSTheme { FavoritesScreen() }
