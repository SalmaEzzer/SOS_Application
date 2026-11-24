# Installation et démarrage

## État du dépôt

Le seul projet exécutable actuellement présent est l’application Android existante à la racine (`app/`). Le backend, l’administration et PostgreSQL sont des emplacements préparatoires et ne sont pas encore des services configurés. Aucun jeu de données fonctionnel n’est fourni.

## Android

1. Installer Android Studio et un JDK compatible avec la version Gradle du wrapper.
2. Ouvrir la racine du dépôt dans Android Studio et laisser Gradle synchroniser les dépendances.
3. Fournir le chemin du SDK Android dans la configuration locale Android Studio ou `local.properties` (ce fichier reste local et ne doit pas être commité).
4. Lancer la configuration `app` sur un émulateur ou appareil de développement.

Le projet est un squelette XML/AppCompat ; Compose/Material 3 est la cible d’architecture mais n’est pas encore configuré dans Gradle.

## Backend et base

Le backend Spring Boot et la configuration PostgreSQL ne sont pas encore initialisés. Avant de les rendre exécutables, valider version Java/Spring Boot, outil de migration, configuration locale (par exemple PostgreSQL local ou Docker Compose), ports et gestion des secrets. Ne pas mettre de vrais mots de passe dans les fichiers suivis par Git.

## Dépannage

- Échec de synchronisation Gradle : vérifier accès aux dépôts Maven/Google, JDK Gradle et version Android SDK requise par le projet.
- SDK introuvable : installer le SDK depuis Android Studio puis définir le chemin localement.
- Ne pas partager `local.properties`, clés de signature, jetons ou fichiers `.env` dans une issue/PR.
