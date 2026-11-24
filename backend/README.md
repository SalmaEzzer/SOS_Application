# Backend SOS

API REST Java 21 / Spring Boot 4.2.0, persistance PostgreSQL et migrations Flyway. Le projet expose uniquement `GET /api/v1/health` à ce stade. L'authentification JWT et les fonctions métier ne sont pas implémentées.

Voir [`../docs/INSTALLATION.md`](../docs/INSTALLATION.md) pour démarrer PostgreSQL et Spring Boot. Lancer les commandes Maven depuis ce dossier avec `.\mvnw.cmd` sous Windows ou `./mvnw` sous macOS/Linux.

## Organisation

Les packages sont organisés par fonctionnalité sous `com.sos.backend`. Chaque fonctionnalité possède les sous-packages `controller`, `service`, `repository`, `entity` et `dto`. Les couches transversales sont dans `config`, `security`, `common` et `exception`. Les packages métier vides sont des emplacements de départ, pas des fonctionnalités.

Les migrations Flyway exécutables se trouvent dans `src/main/resources/db/migration/` pour être incluses dans le JAR. `../database/` contient la documentation des conventions de données. Ne modifiez pas une migration déjà appliquée sur une branche partagée : ajoutez-en une nouvelle.

## Commandes principales

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

Les tests unitaires ne nécessitent pas Docker. Le lancement complet nécessite PostgreSQL et le fichier `.env` à la racine.
