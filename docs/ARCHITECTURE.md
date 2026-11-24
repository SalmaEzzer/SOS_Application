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

Le projet Maven utilise Java 21, Spring Boot 4.2.0 et Maven Wrapper. Le package racine est `com.sos.backend`. L'organisation est modulaire par fonctionnalité :

```text
config/ security/ common/ exception/
auth/ user/ resource/ category/ location/ emergency/
favorite/ proposal/ report/ notification/
  controller/ service/ repository/ entity/ dto/
```

Chaque fonctionnalité ne prend que les couches dont elle a besoin. Les contrôleurs valident/convertissent les entrées et délèguent au service. Les services portent les cas d'usage et transactions. Les repositories accèdent à la persistance ; les entités JPA ne sont jamais exposées directement dans les réponses. Les DTO HTTP restent distincts. Les packages métier sont préparés mais vides ; aucun workflow JWT ou métier n'est encore implémenté.

Responsabilités prévues (à préciser avec les cas d'usage avant d'implémenter) :

| Package | Responsabilité prévue |
|---|---|
| `auth` | Connexion et cycle de vie JWT, après validation du flux et de la politique de sécurité. |
| `user` | Comptes, profil, rôles et préférences utilisateur. |
| `resource` | Répertoire des services d'assistance et informations de vérification. |
| `category` | Catégories et libellés français/arabe. |
| `location` | Communes/coordonnées de la région Rabat-Salé-Kénitra et filtres géographiques. |
| `emergency` | Contacts d'urgence issus de sources officielles vérifiées ; aucun numéro supposé. |
| `favorite` | Associations entre un utilisateur et ses ressources favorites. |
| `proposal` | Propositions de correction ou d'ajout, en attente de modération. |
| `report` | Signalements de problèmes liés à une ressource et suivi de leur traitement. |
| `notification` | Notifications, canaux et préférences de consentement. |

`config` assemble les paramètres d'environnement, CORS et OpenAPI ; `security` porte les règles HTTP et accueillera ensuite JWT ; `common` contient les réponses partagées ; `exception` traduit les erreurs en réponses HTTP communes. Ces packages ne remplacent pas les couches de chaque fonctionnalité.

L'API autorise publiquement uniquement `GET /api/v1/health` et, en profil `dev`, Swagger. Les autres routes sont refusées par défaut. CORS utilise une liste d'origines configurable ; les valeurs de développement ne doivent pas être reprises en production sans validation.

Flyway gère les migrations dans `backend/src/main/resources/db/migration/`. Hibernate utilise `ddl-auto=validate` : il valide les entités lorsqu'elles existeront, mais ne crée ni ne modifie le schéma. Flyway crée le schéma PostgreSQL `sos`, sa table technique d'historique, puis exécute V1 qui s'assure que le schéma existe. Aucune table métier n'est définie.

## Administration et données

`admin/` est réservé à une interface future ; aucune technologie ou fonctionnalité admin n’est présumée. `database/` documente le modèle et les conventions. Les migrations exécutables sont dans `backend/src/main/resources/db/migration/` afin d'être incluses dans le JAR. Elles ne doivent contenir ni données personnelles ni faux répertoire de ressources.

## Échanges et erreurs

L’API est versionnée sous `/api/v1`, utilise JSON UTF-8 et des codes HTTP standards. Les erreurs ont un format stable documenté dans [API.md](API.md). Les secrets et URL d’environnement sont fournis hors du dépôt.

## Tests

Prévoir des tests unitaires pour règles métier/ViewModels/services, des tests d’intégration pour adaptateurs réseau et persistance, et des tests UI ciblés. Ajouter les tests avec la fonctionnalité concernée ; ne pas utiliser de serveur ou compte réel dans les tests.
