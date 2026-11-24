# Décisions à confirmer

Ces choix sont nécessaires avant de rendre le backend déployable ou de concevoir les fonctionnalités. Les valeurs ci-dessous sont des propositions, pas des exigences déjà approuvées.

| Sujet | Proposition de départ | À confirmer |
|---|---|---|
| Package Android | Garder `com.supmti.sos_application` pour préserver l’application créée ; renommer seulement si le propriétaire du namespace est connu. | Namespace final et identité de publication |
| Gradle/SDK Android | Conserver les versions du projet existant à court terme ; faire une revue de compatibilité avant ajout Compose. | Versions cibles supportées et CI |
| UI Android | Migration progressive XML vers Compose + Material 3. | Priorité de migration et maquettes existantes |
| Backend | Java 21, Spring Boot 4.2.0, Maven Wrapper 3.9.16 ; le projet compile et ses tests unitaires passent. | Revoir les mises à jour de sécurité avant chaque livraison |
| Migrations | Flyway retenu ; migrations exécutables dans le classpath du backend. Hibernate est en `validate`. | Définir la politique de rollback et de revue des migrations métier |
| Administration | Dossier réservé, pas de framework présumé. | Admin nécessaire ? Technologie et utilisateurs autorisés |
| JWT | Bearer token proposé. | Émission, renouvellement, expiration, révocation, stockage client et rôles |
| Données métier | Aucun schéma préconçu. | Ressources, signalements, comptes, conservation et règles de confidentialité |
| Environnements | Profils `dev`, `test`, `prod`; PostgreSQL 17 sous Docker Compose pour le socle local. | Origines CORS de production, CI et hébergement cible |
| GitHub | PR relue, branche `main` protégée si possible. | Organisation/projet GitHub et protections disponibles |

Avant chaque décision, documenter le besoin, les options, le choix, les conséquences et la date dans une ADR si l’impact est durable.
