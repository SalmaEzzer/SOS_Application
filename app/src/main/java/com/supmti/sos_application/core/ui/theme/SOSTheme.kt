package com.supmti.sos_application.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object SOSColors {
    val Primary = Color(0xFF2C70B7)
    val Emergency = Color(0xFFEF0009)
    val TextPrimary = Color(0xFF192C47)
    val TextSecondary = Color(0xFF64748B)
    val Background = Color(0xFFFFFFFF)
    val Border = Color(0xFFE2E8F0)
    val LightBlue = Color(0xFFE0EFFC)
    val LightRed = Color(0xFFFDE8E8)
    val LightGreen = Color(0xFFDDF5E5)
    val LightPurple = Color(0xFFEFE8FC)
    val LightYellow = Color(0xFFFFF1D1)
    val Success = Color(0xFF238B57)
    val White = Color(0xFFFFFFFF)
}

object SOSSpacing {
    val XSmall = 4.dp
    val Small = 8.dp
    val Medium = 12.dp
    val Default = 16.dp
    val Large = 24.dp
    val XLarge = 32.dp
    val ScreenHorizontal = 20.dp
    val MinimumTouchTarget = 48.dp
}

object SOSDimensions {
    val CardRadius = 20.dp
    val ButtonRadius = 16.dp
    val ChipRadius = 50.dp
    val CategoryIcon = 44.dp
    val AppBarHeight = 56.dp
    val BottomBarHeight = 76.dp
    val BottomBarTotalHeight = 104.dp
    val EmergencyButton = 64.dp
}

private val SOSLightColors = lightColorScheme(
    primary = SOSColors.Primary,
    onPrimary = SOSColors.White,
    secondary = SOSColors.TextSecondary,
    onSecondary = SOSColors.White,
    error = SOSColors.Emergency,
    onError = SOSColors.White,
    background = SOSColors.Background,
    onBackground = SOSColors.TextPrimary,
    surface = SOSColors.Background,
    onSurface = SOSColors.TextPrimary,
    outline = SOSColors.Border,
)

private val SOSDarkColors = darkColorScheme(
    primary = Color(0xFF8FC4FF),
    onPrimary = Color(0xFF003258),
    secondary = Color(0xFFBEC9D8),
    error = Color(0xFFFFB4AB),
    background = Color(0xFF101820),
    onBackground = Color(0xFFE5EDF7),
    surface = Color(0xFF101820),
    onSurface = Color(0xFFE5EDF7),
    outline = Color(0xFF46576C),
)

private val SOSShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

@Composable
fun SOSTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) SOSDarkColors else SOSLightColors,
        typography = Typography(),
        shapes = SOSShapes,
        content = content,
    )
}
