# SOS Design System Android

La référence visuelle est constituée des cinq captures fournies pour SOS. L’interface Compose reprend leur fond clair, leur bleu d’action, les accents de catégories pastel, les cartes arrondies et la navigation inférieure à cinq destinations avec une action SOS rouge surélevée. La page d’accueil et les écrans temporaires ne prétendent pas fournir de ressources ou contacts opérationnels.

## Palette centralisée

Les valeurs sont déclarées une seule fois dans `app/src/main/java/com/supmti/sos_application/core/ui/theme/SOSTheme.kt`, objet `SOSColors`.

| Jeton | Hex | Usage |
|---|---|---|
| `Primary` | `#2C70B7` | Actions principales, navigation sélectionnée, liens |
| `Emergency` | `#EF0009` | Action et repères d’urgence seulement |
| `TextPrimary` | `#192C47` | Titres et texte principal |
| `TextSecondary` | `#64748B` | Aide, métadonnées et descriptions |
| `Background` / `White` | `#FFFFFF` | Fond des pages et cartes |
| `Border` | `#E2E8F0` | Séparateurs et contours |
| `LightBlue` | `#E0EFFC` | Catégories santé/carte et conteneurs bleus |
| `LightRed` | `#FDE8E8` | Surfaces d’urgence |
| `LightGreen` | `#DDF5E5` | Catégories sociales/santé mentale |
| `LightPurple` | `#EFE8FC` | Catégories d’accompagnement |
| `LightYellow` | `#FFF1D1` | Catégories publiques et avertissements |

Un schéma sombre est fourni dans `SOSTheme`; le thème clair des captures est celui utilisé par défaut. Les couleurs d’état doivent toujours être accompagnées d’un libellé ou d’une icône.

## Typographie

Le socle utilise la typographie Material 3 par défaut (`Typography()`), afin de respecter les réglages de taille de police Android. Utiliser les rôles Material (`headlineSmall`, `titleLarge`, `titleMedium`, `bodyLarge`, `bodyMedium`, `labelLarge`, `labelSmall`) et les poids avec modération. Pas de tailles ou polices locales répétées dans les écrans. Une police de marque ne sera ajoutée qu’après choix explicite et fourniture de ses fichiers/licence.

## Dimensions, espacements et formes

Tokens définis dans `SOSSpacing` et `SOSDimensions` :

- Espacements : 4, 8, 12, 16, 24 et 32 dp ; marge écran horizontale 20 dp.
- Cible tactile minimale commune : 48 dp ; bouton principal/secondaire 52 dp.
- Icône de catégorie 44 dp ; barre supérieure 56 dp ; rail de navigation 76 dp dans une zone totale de 104 dp ; action SOS centrale 64 dp, surélevée avec son contour blanc.
- Barre de recherche 56 dp ; sélecteur de ville et boutons d’icône ont une cible d’au moins 48 dp.
- Carte 20 dp de rayon ; bouton 16 dp ; chip très arrondi (50 dp).
- Les composants utilisent `MaterialTheme.shapes` et les formes déclarées dans `SOSTheme`.

Les valeurs sont des dimensions de mise en page, pas des tailles fixes d’écran. Utiliser `fillMaxWidth`, poids, contraintes `widthIn` et défilement ; vérifier les libellés longs et les tailles d’écran étroites.

## Catalogue commun

Implémentation unique : `core/ui/components/SOSComponents.kt`. Chaque composant possède une Preview Compose dédiée.

| Composant | Usage |
|---|---|
| `SOSPrimaryButton` | Action principale bleue configurable, option icône et état activé |
| `SOSSecondaryButton` | Action secondaire contour Material |
| `SOSEmergencyButton` | Action rouge d’urgence ; désactivée sans cible vérifiée |
| `SOSIconButton` | Icône interactive avec description accessible |
| `SOSSearchBar` | Champ de recherche avec filtre facultatif |
| `SOSFilterChip` | Filtre sélectionnable |
| `SOSCitySelector` | Pastille de ville bleue avec icône de localisation et chevron ; action facultative |
| `SOSCategoryCard` | Carte de catégorie avec icône, couleurs et action |
| `SOSResourceCard` | Carte ressource à partir de `DemoResource`, actions inactives par défaut |
| `SOSEmergencyCard` | Contact d’urgence sans numéro affiché ; appel inactif par défaut |
| `SOSStatusBadge` | Statut textuel |
| `SOSVerifiedBadge` | État vérifié/non vérifié configurable ; ne signifie pas qu’un exemple est vérifié |
| `SOSTopBar` | Barre de titre avec retour et contenu d’actions optionnel |
| `SOSBottomNavigation` | Accueil, recherche, SOS, carte, favoris ; SOS central rouge circulaire surélevé |
| `SOSEmptyState` | État vide avec texte et action facultative |
| `SOSLoadingState` | Chargement avec indicateur et libellé |
| `SOSErrorState` | Erreur compréhensible et action de reprise |
| `SOSSectionHeader` | Titre de section et action facultative |

Le catalogue visuel se trouve dans l’écran interne « Catalogue des composants SOS », accessible depuis l’icône en haut de l’accueil en variante debug uniquement (`BuildConfig.DEBUG`). La route et l’icône sont absentes de la variante release. Les données de preview et de démonstration sont explicitement fictives.

## Exemples d’utilisation

```kotlin
SOSPrimaryButton(text = "Continuer", onClick = onContinue)

SOSCategoryCard(
    title = "Santé",
    icon = Icons.Outlined.LocalHospital,
    onClick = onCategorySelected,
    containerColor = SOSColors.LightBlue,
    iconTint = SOSColors.Primary,
)

SOSResourceCard(
    resource = demoResourceExample,
    icon = Icons.Outlined.Info,
) // actions désactivées par défaut en mode démonstration

SOSEmergencyCard(title = "Police secours", icon = Icons.Outlined.LocalPolice)
// Un numéro vérifié devient exigé pour rendre le bouton d’appel actif.
```

## Règles obligatoires pour l’équipe

1. Ne définissez jamais une couleur, un rayon ou un espacement partagé directement dans une feature : ajoutez/choisissez un token ici.
2. Recherchez un composant existant avant d’en créer un. N’ajoutez une abstraction commune que si son comportement est réutilisé et stable.
3. Composez avec Material 3 ; les icônes sont des vecteurs Material (`ImageVector`), jamais des emojis.
4. Donnez un `contentDescription` aux icônes actionnables ; omettez-le seulement pour une icône décorative accompagnée du même texte.
5. Toute commande doit avoir un état activé/désactivé honnête, une cible tactile d’au moins 48 dp et un libellé accessible.
6. Les vrais numéros d’urgence nécessitent une source officielle vérifiée et documentée. Sans cela, aucun numéro n’est affiché et les appels restent désactivés.
7. Une ressource fictive ne doit jamais apparaître comme réelle, ouverte, disponible ou vérifiée. Étiquetez les previews/fixtures « démonstration » et n’en faites pas des actions réelles.
8. Les écrans couvrent chargement, erreur, vide et contenu si ces états existent ; ne remplacez pas un état vide par de fausses données.
9. Préservez les réglages système de police et testez petits écrans, texte agrandi, TalkBack, contraste et thèmes clair/sombre.
10. Ajoutez une Preview Compose pour chaque nouveau composant partagé ; vérifiez aussi l’écran complet à l’émulateur quand sa présentation change.
