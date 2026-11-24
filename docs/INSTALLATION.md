# Installation et démarrage

## État du dépôt

Le seul projet exécutable actuellement présent est l’application Android existante à la racine (`app/`). Le backend, l’administration et PostgreSQL sont des emplacements préparatoires et ne sont pas encore des services configurés. Aucun jeu de données fonctionnel n’est fourni.

## Android

1. Installer Android Studio et le JDK 17 minimum utilisé par Android Gradle Plugin 9.5. Le wrapper utilise Gradle 9.6.0 ; sa configuration de daemon demande Java 25.
2. Ouvrir la racine du dépôt dans Android Studio et laisser Gradle synchroniser les dépendances.
3. Fournir le chemin du SDK Android dans la configuration locale Android Studio ou `local.properties` (ce fichier reste local et ne doit pas être commité).
4. Lancer la configuration `app` sur un émulateur ou appareil de développement.

La configuration du dépôt utilise Android Gradle Plugin 9.5.2 avec Kotlin intégré à AGP 9, Compose Compiler 2.4.10, Compose BOM 2026.09.00, Navigation Compose 2.10.2 et SDK Android compile/target 37 (min 24). En cas de message Java incompatible, sélectionner le JDK 25 configuré pour Gradle dans Android Studio : **Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK**.

Pour lancer dans Android Studio : ouvrir la racine `SOS_Application`, attendre « Sync Project with Gradle Files », sélectionner la configuration `app`, choisir un émulateur/API 24+ ou appareil, puis cliquer sur Run. La première compilation requiert le téléchargement des dépendances et les SDK API 37/Build Tools requis.

En ligne de commande PowerShell, depuis la racine :

```powershell
.\gradlew.bat :app:assembleDebug
.\gradlew.bat :app:testDebugUnitTest
.\gradlew.bat :app:connectedDebugAndroidTest
```

La dernière commande demande un appareil ou émulateur connecté. L’APK debug se trouve après compilation dans `app/build/outputs/apk/debug/app-debug.apk`.

## Backend et base

Le backend Spring Boot et la configuration PostgreSQL ne sont pas encore initialisés. Avant de les rendre exécutables, valider version Java/Spring Boot, outil de migration, configuration locale (par exemple PostgreSQL local ou Docker Compose), ports et gestion des secrets. Ne pas mettre de vrais mots de passe dans les fichiers suivis par Git.

## Dépannage

- Échec de synchronisation Gradle : vérifier accès aux dépôts Maven/Google, JDK Gradle et version Android SDK requise par le projet.
- SDK introuvable : installer le SDK depuis Android Studio puis définir le chemin localement.
- Ne pas partager `local.properties`, clés de signature, jetons ou fichiers `.env` dans une issue/PR.
