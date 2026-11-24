# SOS

Socle technique d’une application mobile d’assistance permettant de rechercher des ressources et services, consulter leurs informations, signaler des problèmes et accéder à des contacts d’urgence. Ce dépôt prépare l’architecture ; il ne contient pas encore ces fonctionnalités ni un catalogue réel de ressources.

## Modules

| Répertoire | Responsabilité | État |
|---|---|---|
| [`app/`](app) | Application Android Kotlin, Jetpack Compose, Material 3 et Navigation Compose. Écrans temporaires ; catalogue visible en debug uniquement. | Fondation Android |
| [`backend/`](backend) | API REST Java 21 / Spring Boot, Spring Security et Spring Data JPA/PostgreSQL. | Fondation exécutable, JWT et métier à venir |
| [`admin/`](admin) | Point de départ pour une future interface de gestion. | Technologie à valider |
| [`database/`](database) | Conventions PostgreSQL ; migrations Flyway exécutables dans `backend/src/main/resources/db/migration/`. | Initial |
| [`docs/`](docs) | Architecture, API, installation, décisions et guides. | Initial |

## Documentation

- [Architecture](docs/ARCHITECTURE.md)
- [Base de données](docs/DATABASE.md)
- [API](docs/API.md)
- [Design system](docs/DESIGN_SYSTEM.md)
- [Installation](docs/INSTALLATION.md)
- [Guide d’équipe](docs/TEAM_GUIDE.md)
- [Décisions à valider](docs/DECISIONS.md)

## Principes

- Garder les dépendances orientées vers le domaine et séparer UI, logique métier et accès aux données.
- Ne jamais versionner secrets, jetons, mots de passe, données personnelles ou fichiers locaux Android.
- Aucune donnée fictive ne doit être présentée comme une information réelle. Utiliser des jeux de test clairement identifiés et isolés.
- Toute évolution passe par une branche et une Pull Request relue.

Voir [INSTALLATION.md](docs/INSTALLATION.md) pour l’état actuel et les prérequis.

## Démarrer le backend

Le backend utilise Java 21 et Maven Wrapper. PostgreSQL se lance avec Docker Compose. Les commandes détaillées et expliquées figurent dans [docs/INSTALLATION.md](docs/INSTALLATION.md).
