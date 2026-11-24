# Installation et démarrage

## État du dépôt

L'application Android (`app/`) et la fondation du backend (`backend/`) sont présentes. PostgreSQL se lance avec Docker Compose. L'administration reste un emplacement préparatoire. Aucune ressource SOS réelle ou donnée métier n'est fournie.

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

## Backend et PostgreSQL

### Prérequis

- JDK 21. Vérifier la version utilisée par Maven avec `mvn -version` ; la ligne `Java version` doit indiquer 21. Le backend contient Maven Wrapper, donc Maven n'a pas besoin d'être installé pour la suite.
- Docker Desktop avec le moteur Linux démarré. Vérifier avec `docker info` et `docker compose version`.
- Git pour récupérer le dépôt.

### Préparer la configuration locale

Depuis la racine du dépôt, vérifie d'abord si le fichier local existe :

```powershell
Test-Path .env
```

Si la commande répond `False`, copie le modèle :

```powershell
Copy-Item .env.example .env
```

Si elle répond `True`, garde le fichier existant et ne relance pas `Copy-Item`. Ouvre `.env` et remplace la valeur exemple de `POSTGRES_PASSWORD` par un mot de passe local fort. Ce fichier est ignoré par Git. Ne partage pas son contenu et ne mets jamais de secret dans `.env.example`.

### Démarrer PostgreSQL

Toujours depuis la racine du dépôt :

```powershell
docker compose up -d postgres
```

Cette commande télécharge l'image si nécessaire et démarre PostgreSQL en arrière-plan. Le volume Docker `sos-postgres-data` conserve les données après l'arrêt du conteneur. Vérifie l'état et le contrôle de disponibilité :

```powershell
docker compose ps
docker compose logs postgres
```

Dans `docker compose ps`, attends l'état `healthy`. La commande `logs` affiche les messages du serveur pour diagnostiquer un problème de démarrage.

### Compiler et tester le backend

Depuis le dossier `backend` :

```powershell
cd backend
.\mvnw.cmd test
.\mvnw.cmd package
```

`mvnw.cmd` est le Maven Wrapper inclus au projet ; à sa première exécution il télécharge Maven 3.9.16. `test` compile le code et exécute les tests unitaires. `package` construit aussi le JAR dans `backend/target/`.

### Lancer l'API en développement

Dans le dossier `backend`, lance :

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

Le profil `dev` active Swagger et les origines CORS de développement. Le backend lit le fichier `.env` à la racine pour se connecter à PostgreSQL. Vérifie l'API dans un deuxième terminal :

```powershell
Invoke-RestMethod http://localhost:8080/api/v1/health
```

La réponse attendue est `status: UP` et `service: sos-backend`. Swagger est disponible à <http://localhost:8080/swagger-ui/index.html> et OpenAPI à <http://localhost:8080/v3/api-docs>.

Pour arrêter l'API, utilise `Ctrl+C` dans son terminal. Pour arrêter PostgreSQL sans supprimer les données : depuis la racine, lance `docker compose stop postgres`. Pour arrêter et retirer le conteneur en conservant le volume, utilise `docker compose down`. Ne lance pas `docker compose down -v` sauf si tu veux supprimer définitivement les données locales du volume.

### Profils et sécurité de configuration

- `dev` : Swagger disponible ; CORS limité par défaut aux origines locales `localhost:3000` et `localhost:5173`.
- `test` : configuration isolée pour les tests, migration Flyway active.
- `prod` : Swagger désactivé et messages d'erreur masqués. Fournir les paramètres DB/CORS par variables d'environnement et utiliser une base dédiée. Sans `SOS_CORS_ALLOWED_ORIGINS`, aucune origine navigateur n'est autorisée.

Hibernate est configuré en `ddl-auto=validate` dans tous les profils. Flyway est la source de vérité du schéma ; V1 s'assure seulement que le schéma `sos` existe. Flyway ajoute sa propre table technique d'historique, mais aucune table métier n'est créée.

### Dépannage du backend

- `docker info` ne répond pas : démarrer Docker Desktop et attendre que le moteur soit prêt.
- Port `5432` déjà utilisé : arrêter le service PostgreSQL local ou changer `POSTGRES_PORT` dans `.env` (et conserver la même valeur pour l'URL `DB_URL` si elle est configurée).
- Échec d'authentification PostgreSQL après modification du mot de passe : un volume existant conserve l'ancien mot de passe. Pour repartir à zéro, sauvegarde d'abord les données ; la suppression du volume est destructive.
- `mvnw.cmd` ne trouve pas Java : installer un JDK 21 et définir `JAVA_HOME` vers ce JDK.
- Les endpoints métier et l'authentification JWT ne sont pas encore implémentés. Toutes les routes non explicitement publiques sont refusées.

## Dépannage

- Échec de synchronisation Gradle : vérifier accès aux dépôts Maven/Google, JDK Gradle et version Android SDK requise par le projet.
- SDK introuvable : installer le SDK depuis Android Studio puis définir le chemin localement.
- Ne pas partager `local.properties`, clés de signature, jetons ou fichiers `.env` dans une issue/PR.
