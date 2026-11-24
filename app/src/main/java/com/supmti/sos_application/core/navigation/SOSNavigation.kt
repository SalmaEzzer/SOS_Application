package com.supmti.sos_application.core.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.PhoneInTalk
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.supmti.sos_application.BuildConfig
import com.supmti.sos_application.core.ui.theme.SOSColors
import com.supmti.sos_application.core.ui.theme.SOSDimensions
import com.supmti.sos_application.core.ui.theme.SOSTheme
import com.supmti.sos_application.features.emergency.EmergencyScreen
import com.supmti.sos_application.features.favorites.FavoritesScreen
import com.supmti.sos_application.features.home.HomeScreen
import com.supmti.sos_application.features.map.MapScreen
import com.supmti.sos_application.features.resources.ComponentGalleryScreen
import com.supmti.sos_application.features.search.SearchScreen

private object SOSRoutes {
    const val Home = "home"
    const val Search = "search"
    const val Emergency = "emergency"
    const val Map = "map"
    const val Favorites = "favorites"
    const val ComponentGallery = "component-gallery"
}

private data class BottomDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
)

@Composable
fun SOSNavigation() {
    val navController = rememberNavController()
    val navEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navEntry?.destination?.route ?: SOSRoutes.Home

    Scaffold(
        containerColor = SOSColors.Background,
        bottomBar = {
            SOSBottomNavigation(
                currentRoute = currentRoute,
                onNavigate = { destination ->
                    navController.navigate(destination) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SOSRoutes.Home,
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        ) {
            composable(SOSRoutes.Home) {
                HomeScreen(
                    onSearch = { navController.navigate(SOSRoutes.Search) },
                    onEmergency = { navController.navigate(SOSRoutes.Emergency) },
                    onOpenComponents = if (BuildConfig.DEBUG) {
                        { navController.navigate(SOSRoutes.ComponentGallery) }
                    } else {
                        null
                    },
                )
            }
            composable(SOSRoutes.Search) { SearchScreen() }
            composable(SOSRoutes.Emergency) { EmergencyScreen() }
            composable(SOSRoutes.Map) { MapScreen() }
            composable(SOSRoutes.Favorites) { FavoritesScreen(onFindHelp = { navController.navigate(SOSRoutes.Search) }) }
            if (BuildConfig.DEBUG) {
                composable(SOSRoutes.ComponentGallery) { ComponentGalleryScreen() }
            }
        }
    }
}

@Composable
fun SOSBottomNavigation(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val destinations = listOf(
        BottomDestination(SOSRoutes.Home, "Accueil", Icons.Outlined.Home),
        BottomDestination(SOSRoutes.Search, "Recherche", Icons.Outlined.Search),
        BottomDestination(SOSRoutes.Map, "Carte", Icons.Outlined.Map),
        BottomDestination(SOSRoutes.Favorites, "Favoris", Icons.Outlined.FavoriteBorder),
    )
    Surface(
        modifier = modifier.fillMaxWidth().navigationBarsPadding(),
        color = SOSColors.White,
        border = BorderStroke(0.5.dp, SOSColors.Border),
    ) {
        Box(Modifier.fillMaxWidth().height(SOSDimensions.BottomBarTotalHeight)) {
            Row(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(SOSDimensions.BottomBarHeight),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NavigationBarItem(
                    selected = currentRoute == destinations[0].route,
                    onClick = { onNavigate(destinations[0].route) },
                    icon = { Icon(destinations[0].icon, contentDescription = null) },
                    label = { Text(destinations[0].label, maxLines = 1, softWrap = false) },
                    colors = sosNavigationItemColors(),
                    modifier = Modifier.weight(1f),
                )
                NavigationBarItem(
                    selected = currentRoute == destinations[1].route,
                    onClick = { onNavigate(destinations[1].route) },
                    icon = { Icon(destinations[1].icon, contentDescription = null) },
                    label = { Text(destinations[1].label, maxLines = 1, softWrap = false) },
                    colors = sosNavigationItemColors(),
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.weight(1f))
                NavigationBarItem(
                    selected = currentRoute == destinations[2].route,
                    onClick = { onNavigate(destinations[2].route) },
                    icon = { Icon(destinations[2].icon, contentDescription = null) },
                    label = { Text(destinations[2].label, maxLines = 1, softWrap = false) },
                    colors = sosNavigationItemColors(),
                    modifier = Modifier.weight(1f),
                )
                NavigationBarItem(
                    selected = currentRoute == destinations[3].route,
                    onClick = { onNavigate(destinations[3].route) },
                    icon = { Icon(destinations[3].icon, contentDescription = null) },
                    label = { Text(destinations[3].label, maxLines = 1, softWrap = false) },
                    colors = sosNavigationItemColors(),
                    modifier = Modifier.weight(1f),
                )
            }
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                Box(
                    modifier = Modifier.size(SOSDimensions.EmergencyButton + 8.dp).background(SOSColors.White, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    FloatingActionButton(
                        onClick = { onNavigate(SOSRoutes.Emergency) },
                        modifier = Modifier.size(SOSDimensions.EmergencyButton),
                        shape = CircleShape,
                        containerColor = SOSColors.Emergency,
                        contentColor = Color.White,
                    ) {
                        Icon(Icons.Outlined.PhoneInTalk, contentDescription = "SOS, contacts d’urgence", modifier = Modifier.size(28.dp))
                    }
                }
                Text("SOS", color = SOSColors.Emergency, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
private fun sosNavigationItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = SOSColors.Primary,
    selectedTextColor = SOSColors.Primary,
    unselectedIconColor = SOSColors.TextSecondary,
    unselectedTextColor = SOSColors.TextSecondary,
    indicatorColor = Color.Transparent,
)

@Preview(showBackground = true)
@Composable
private fun SOSBottomNavigationPreview() {
    SOSTheme {
        SOSBottomNavigation(currentRoute = "home", onNavigate = {})
    }
}
