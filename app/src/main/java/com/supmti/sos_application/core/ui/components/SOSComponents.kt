package com.supmti.sos_application.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supmti.sos_application.core.model.DemoResource
import com.supmti.sos_application.core.model.demoResourceExample
import com.supmti.sos_application.core.ui.theme.SOSColors
import com.supmti.sos_application.core.ui.theme.SOSDimensions
import com.supmti.sos_application.core.ui.theme.SOSSpacing
import com.supmti.sos_application.core.ui.theme.SOSTheme

@Composable
fun SOSPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.minimumInteractiveComponentSize().height(52.dp),
        shape = RoundedCornerShape(SOSDimensions.ButtonRadius),
        colors = ButtonDefaults.buttonColors(containerColor = SOSColors.Primary),
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null)
            Spacer(Modifier.width(SOSSpacing.Small))
        }
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun SOSSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.minimumInteractiveComponentSize().height(52.dp),
        shape = RoundedCornerShape(SOSDimensions.ButtonRadius),
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = SOSColors.Primary)
            Spacer(Modifier.width(SOSSpacing.Small))
        }
        Text(text, color = SOSColors.TextPrimary)
    }
}

@Composable
fun SOSEmergencyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.minimumInteractiveComponentSize().height(52.dp),
        shape = RoundedCornerShape(SOSDimensions.ButtonRadius),
        colors = ButtonDefaults.buttonColors(containerColor = SOSColors.Emergency),
    ) {
        Icon(Icons.Outlined.Phone, contentDescription = null)
        Spacer(Modifier.width(SOSSpacing.Small))
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SOSIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = SOSColors.TextSecondary,
    enabled: Boolean = true,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.minimumInteractiveComponentSize(),
    ) {
        Icon(icon, contentDescription = contentDescription, tint = tint)
    }
}

@Composable
fun SOSSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Rechercher un service…",
    onFilterClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small),
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f).height(56.dp),
            placeholder = {
                Text(
                    placeholder,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Clip,
                )
            },
            leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(SOSDimensions.CardRadius),
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )
        if (onFilterClick != null) {
            SOSIconButton(
                onClick = onFilterClick,
                icon = Icons.Outlined.Tune,
                contentDescription = "Filtres",
                tint = SOSColors.Primary,
                modifier = Modifier
                    .size(48.dp)
                    .background(SOSColors.LightBlue, CircleShape),
            )
        }
    }
}

@Composable
fun SOSCitySelector(
    cityName: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .heightIn(min = SOSSpacing.MinimumTouchTarget)
            .clip(RoundedCornerShape(SOSDimensions.ChipRadius))
            .background(SOSColors.LightBlue, RoundedCornerShape(SOSDimensions.ChipRadius))
            .then(
                if (onClick == null) Modifier
                else Modifier.clickable(role = Role.Button, onClick = onClick),
            )
            .padding(horizontal = SOSSpacing.Medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small),
    ) {
        Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = SOSColors.Primary)
        Text(cityName, color = SOSColors.TextPrimary, style = MaterialTheme.typography.labelLarge)
        Icon(
            Icons.Outlined.ExpandMore,
            contentDescription = if (onClick == null) null else "Choisir une ville",
            tint = SOSColors.TextSecondary,
        )
    }
}

@Composable
fun SOSFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.minimumInteractiveComponentSize(),
        label = { Text(label) },
        shape = RoundedCornerShape(SOSDimensions.ChipRadius),
    )
}

@Composable
fun SOSCategoryCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = SOSColors.LightBlue,
    iconTint: Color = SOSColors.Primary,
) {
    Card(
        onClick = onClick,
        modifier = modifier.minimumInteractiveComponentSize(),
        shape = RoundedCornerShape(SOSDimensions.CardRadius),
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 76.dp)
                .padding(SOSSpacing.Default),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Medium),
        ) {
            Box(
                modifier = Modifier
                    .size(SOSDimensions.CategoryIcon)
                    .background(SOSColors.White.copy(alpha = 0.78f), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon, contentDescription = null, tint = iconTint)
            }
            Text(
                title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = SOSColors.TextPrimary,
            )
        }
    }
}

@Composable
fun SOSResourceCard(
    resource: DemoResource,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    actionsEnabled: Boolean = false,
    onDetails: () -> Unit = {},
    onDirections: () -> Unit = {},
    onCall: () -> Unit = {},
) {
    val callEnabled = actionsEnabled && resource.canCall
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(SOSDimensions.CardRadius),
        colors = CardDefaults.elevatedCardColors(containerColor = SOSColors.White),
    ) {
        Column(Modifier.padding(SOSSpacing.Default), verticalArrangement = Arrangement.spacedBy(SOSSpacing.Medium)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(48.dp).background(SOSColors.LightBlue, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center,
                ) { Icon(icon, contentDescription = null, tint = SOSColors.Primary) }
                Spacer(Modifier.width(SOSSpacing.Medium))
                Column(Modifier.weight(1f)) {
                    Text(resource.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                    Text(resource.category, style = MaterialTheme.typography.labelLarge, color = SOSColors.Primary)
                }
                SOSIconButton(
                    onClick = {},
                    icon = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Ajouter aux favoris (démonstration)",
                    enabled = actionsEnabled,
                )
            }
            Text(
                resource.description,
                color = SOSColors.TextSecondary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small)) {
                Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = SOSColors.TextSecondary, modifier = Modifier.size(18.dp))
                Text(resource.locationLabel, color = SOSColors.TextSecondary, style = MaterialTheme.typography.labelMedium)
                SOSStatusBadge("Démonstration", background = SOSColors.LightYellow, foreground = SOSColors.TextPrimary)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Small), verticalAlignment = Alignment.CenterVertically) {
                SOSPrimaryButton("Détails", onDetails, modifier = Modifier.weight(1f), enabled = actionsEnabled)
                SOSSecondaryButton("Itinéraire", onDirections, icon = Icons.Outlined.Navigation, enabled = actionsEnabled)
                SOSIconButton(
                    onClick = onCall,
                    icon = Icons.Outlined.Phone,
                    contentDescription = if (callEnabled) "Appeler ${resource.name}" else "Appel désactivé : aucun numéro vérifié",
                    tint = if (callEnabled) SOSColors.Emergency else SOSColors.TextSecondary,
                    enabled = callEnabled,
                    modifier = Modifier.background(
                        if (callEnabled) SOSColors.LightRed else SOSColors.Border,
                        CircleShape,
                    ),
                )
            }
        }
    }
}

@Composable
fun SOSEmergencyCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    verifiedPhoneNumber: String? = null,
    onCall: (String) -> Unit = {},
) {
    val canCall = !verifiedPhoneNumber.isNullOrBlank()
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(SOSDimensions.CardRadius),
    ) {
        Column(Modifier.padding(SOSSpacing.Default), verticalArrangement = Arrangement.spacedBy(SOSSpacing.Medium)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(SOSSpacing.Medium)) {
                Box(Modifier.size(48.dp).background(SOSColors.LightRed, CircleShape), contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = SOSColors.Emergency)
                }
                Column {
                    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text("Numéro officiel à vérifier", style = MaterialTheme.typography.bodySmall, color = SOSColors.TextSecondary)
                }
            }
            SOSEmergencyButton(
                text = if (canCall) "Appeler" else "Appel indisponible",
                onClick = { verifiedPhoneNumber?.let(onCall) },
                modifier = Modifier.fillMaxWidth(),
                enabled = canCall,
            )
        }
    }
}

@Composable
fun SOSStatusBadge(
    text: String,
    modifier: Modifier = Modifier,
    background: Color = SOSColors.LightGreen,
    foreground: Color = SOSColors.Success,
) {
    Surface(modifier = modifier, color = background, shape = RoundedCornerShape(SOSDimensions.ChipRadius)) {
        Text(text, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), color = foreground, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun SOSVerifiedBadge(
    verified: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(if (verified) SOSColors.LightGreen else SOSColors.LightYellow, RoundedCornerShape(SOSDimensions.ChipRadius))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            if (verified) Icons.Outlined.CheckCircle else Icons.Outlined.Info,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = if (verified) SOSColors.Success else SOSColors.TextSecondary,
        )
        Text(if (verified) "Vérifié" else "Non vérifié", color = if (verified) SOSColors.Success else SOSColors.TextSecondary, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun SOSTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth().height(SOSDimensions.AppBarHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBack != null) {
            SOSIconButton(onBack, Icons.AutoMirrored.Outlined.ArrowBack, "Retour")
            Spacer(Modifier.width(SOSSpacing.Small))
        }
        Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        actions()
    }
}

@Composable
fun SOSEmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.FavoriteBorder,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(SOSSpacing.XLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Medium),
    ) {
        Box(Modifier.size(88.dp).background(SOSColors.LightRed, CircleShape), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = SOSColors.Emergency, modifier = Modifier.size(36.dp))
        }
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(message, style = MaterialTheme.typography.bodyLarge, color = SOSColors.TextSecondary, textAlign = TextAlign.Center)
        if (actionLabel != null && onAction != null) SOSPrimaryButton(actionLabel, onAction)
    }
}

@Composable
fun SOSLoadingState(
    message: String = "Chargement…",
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(SOSSpacing.XLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Medium),
    ) {
        CircularProgressIndicator(color = SOSColors.Primary)
        Text(message, color = SOSColors.TextSecondary)
    }
}

@Composable
fun SOSErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(SOSSpacing.Large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SOSSpacing.Medium),
    ) {
        Icon(Icons.Outlined.Info, contentDescription = null, tint = SOSColors.Emergency, modifier = Modifier.size(32.dp))
        Text(message, color = SOSColors.TextSecondary, textAlign = TextAlign.Center)
        SOSSecondaryButton("Réessayer", onRetry)
    }
}

@Composable
fun SOSSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        if (actionLabel != null && onAction != null) TextButton(onClick = onAction) { Text(actionLabel) }
    }
}

@Preview(showBackground = true)
@Composable private fun SOSPrimaryButtonPreview() = SOSTheme { SOSPrimaryButton("Continuer", {}) }
@Preview(showBackground = true)
@Composable private fun SOSSecondaryButtonPreview() = SOSTheme { SOSSecondaryButton("Itinéraire", {}, icon = Icons.Outlined.Navigation) }
@Preview(showBackground = true)
@Composable private fun SOSEmergencyButtonPreview() = SOSTheme { SOSEmergencyButton("Appel indisponible", {}, enabled = false) }
@Preview(showBackground = true)
@Composable private fun SOSIconButtonPreview() = SOSTheme { SOSIconButton({}, Icons.Outlined.Tune, "Filtres") }
@Preview(showBackground = true)
@Composable private fun SOSSearchBarPreview() = SOSTheme { SOSSearchBar("", {}, onFilterClick = {}) }
@Preview(showBackground = true)
@Composable private fun SOSCitySelectorPreview() = SOSTheme { SOSCitySelector("Témara", {}) }
@Preview(showBackground = true)
@Composable private fun SOSFilterChipPreview() = SOSTheme { SOSFilterChip("Santé", false, {}) }
@Preview(showBackground = true)
@Composable private fun SOSCategoryCardPreview() = SOSTheme { SOSCategoryCard("Santé", Icons.Outlined.Info, {}) }
@Preview(showBackground = true)
@Composable private fun SOSResourceCardPreview() = SOSTheme { SOSResourceCard(demoResourceExample, Icons.Outlined.Info) }
@Preview(showBackground = true)
@Composable private fun SOSEmergencyCardPreview() = SOSTheme { SOSEmergencyCard("Police secours", Icons.Outlined.Info) }
@Preview(showBackground = true)
@Composable private fun SOSStatusBadgePreview() = SOSTheme { SOSStatusBadge("Démonstration", background = SOSColors.LightYellow, foreground = SOSColors.TextPrimary) }
@Preview(showBackground = true)
@Composable private fun SOSVerifiedBadgePreview() = SOSTheme { SOSVerifiedBadge(verified = false) }
@Preview(showBackground = true)
@Composable private fun SOSTopBarPreview() = SOSTheme { SOSTopBar("Accueil") }
@Preview(showBackground = true)
@Composable private fun SOSEmptyStatePreview() = SOSTheme { SOSEmptyState("Aucun favori", "Vos ressources enregistrées apparaîtront ici.") }
@Preview(showBackground = true)
@Composable private fun SOSLoadingStatePreview() = SOSTheme { SOSLoadingState() }
@Preview(showBackground = true)
@Composable private fun SOSErrorStatePreview() = SOSTheme { SOSErrorState("Une erreur est survenue.", {}) }
@Preview(showBackground = true)
@Composable private fun SOSSectionHeaderPreview() = SOSTheme {
    SOSSectionHeader(title = "Catégories", actionLabel = "Voir tout", onAction = {})
}
