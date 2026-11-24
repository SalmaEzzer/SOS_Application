# Architecture SOS

## Vue globale

Le dépôt est un monorepo composé de clients et services indépendants communiquant par HTTP REST avec JSON. PostgreSQL est la persistance du backend ; l’application Android ne se connecte jamais directement à la base.

```text
Android (Compose → ViewModel/StateFlow → use cases → Repository → API REST)
                                                                  ↓
Admin (future interface) → API REST Spring Boot → services métier
                                                   → Spring Data JPA → PostgreSQL
```

L’authentification cible des jetons JWT via Spring Security. Les modalités de durée, renouvellement, révocation et stockage des jetons doivent être arrêtées avant l’implémentation de l’authentification.

## Android (`app/`)

Le module Android existant et ses ressources XML sont conservés. `MainActivity` utilise maintenant `ComponentActivity` et Compose avec Material 3 ; Navigation Compose relie les cinq onglets. Le catalogue de composants est enregistré et accessible uniquement dans les variantes debug (`BuildConfig.DEBUG`). L’interface suit les cinq captures de référence sans refonte arbitraire.

Organisation cible par fonctionnalité, à introduire au fil des besoins :

```text
app/src/main/java/com/supmti/sos_application/
  core/ui/theme/       # Tokens SOS et thème Material 3
  core/ui/components/  # Composants partagés Compose
  core/navigation/     # Navigation commune et barre inférieure
  core/network/        # Résultat réseau partagé (pas de client configuré)
  core/model/          # Modèle local étiqueté démonstration
  features/<feature>/  # Écrans Compose provisoires par destination
```

Les destinations et composants communs sont posés ; les ViewModels, StateFlow, cas d’usage et repositories métier restent à introduire avec les fonctionnalités réelles. À terme, chaque feature possèdera son UI et ViewModel. Le ViewModel exposera un `StateFlow<UiState>` immuable et déléguera au domaine. Le domaine ne dépendra ni d’Android, ni de Compose, ni d’un client HTTP. Les repositories cacheront les sources de données et traduiront les erreurs en résultats métier compréhensibles.

## Backend (`backend/`)

Organisation cible par couches sous `com.sos` :

```text
config/       configuration et infrastructure transversale
security/     filtres, principal et configuration Spring Security/JWT
common/       erreurs et réponses communes
<feature>/api contrôleurs et DTO HTTP
<feature>/application services et cas d’usage
<feature>/domain modèles et règles métier
<feature>/infrastructure entités JPA et repositories
```

Les contrôleurs valident/convertissent les entrées et délèguent à la couche applicative. Ils ne portent pas les règles métier ni les requêtes JPA. Les entités JPA ne sont pas exposées directement dans les réponses API. Les transactions sont délimitées dans la couche applicative. Les migrations SQL versionnées sont la source de vérité du schéma ; Hibernate ne doit pas modifier automatiquement le schéma en production.

## Administration et données

`admin/` est réservé à une interface future ; aucune technologie ou fonctionnalité admin n’est présumée. `database/migrations/` accueillera les migrations PostgreSQL ordonnées. Les migrations ne doivent contenir ni données personnelles ni faux répertoire de ressources.

## Échanges et erreurs

L’API est versionnée sous `/api/v1`, utilise JSON UTF-8 et des codes HTTP standards. Les erreurs ont un format stable documenté dans [API.md](API.md). Les secrets et URL d’environnement sont fournis hors du dépôt.

## Tests

Prévoir des tests unitaires pour règles métier/ViewModels/services, des tests d’intégration pour adaptateurs réseau et persistance, et des tests UI ciblés. Ajouter les tests avec la fonctionnalité concernée ; ne pas utiliser de serveur ou compte réel dans les tests.
