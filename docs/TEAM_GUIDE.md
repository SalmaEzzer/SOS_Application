# Guide d’équipe (6 personnes, niveaux débutants)

## Répartition

La répartition est indicative ; chaque fonctionnalité reste revue par une autre personne.

| Domaine | Responsabilité |
|---|---|
| Android UI | Écrans Compose, navigation et thème partagé |
| Android data | API cliente, DTO, repositories et erreurs réseau |
| Android domaine | États, cas d’usage et coordination avec les contrats API |
| Backend API | Contrôleurs, DTO HTTP et validation |
| Backend métier/sécurité | Services, règles métier et Spring Security/JWT |
| Données/intégration | Migrations, PostgreSQL, documentation et intégration transverse |

Faire tourner les rôles pour partager les connaissances. Aucun module n’appartient exclusivement à une personne.

## Conventions de nommage

- Kotlin : `UpperCamelCase` pour types, `lowerCamelCase` pour propriétés/fonctions, constantes `UPPER_SNAKE_CASE` seulement pour les constantes réellement globales.
- Java : `UpperCamelCase` pour types, `lowerCamelCase` pour membres ; packages en minuscules.
- Fichiers et répertoires : noms explicites en minuscules ; SQL `V<version>__<description_en_snake_case>.sql` (si Flyway est validé).
- Android : suffixer clairement `ViewModel`, `Repository`, `DataSource`, `Dto`, `Entity` selon la couche. Éviter les noms génériques `Manager`/`Utils` sans rôle précis.
- API : chemins de ressources au pluriel ; JSON `camelCase`.

## Git et GitHub

- `main` contient un état intégrable ; elle est protégée contre les poussées directes lorsque les règles GitHub sont disponibles.
- Une branche courte par changement : `feat/description`, `fix/description`, `docs/description`, `refactor/description`, `chore/description`.
- Commits : format simple de type Conventional Commits, par exemple `feat(android): ajoute le shell de navigation`, `docs: précise le contrat API`. Un commit décrit une modification cohérente.
- Ouvrir une Pull Request vers `main`, expliquer contexte, changements, limites et vérifications ; lier l’issue si elle existe ; ajouter captures pour changement UI.
- Au moins une revue d’un coéquipier avant fusion ; auteur répond aux commentaires et garde la PR focalisée. Pas d’auto-approbation.
- Avant fusion : vérifier compilation et validations pertinentes, relire diff et secrets, migrations, accessibilité ou compatibilité API selon la portée. Ne pas affirmer une vérification qui n’a pas été exécutée.
- Résoudre les conflits avec compréhension du code et demander l’aide d’un coéquipier plutôt que supprimer des changements inconnus.

## Règles de développement

- Pas de logique métier dans l’UI ou le contrôleur HTTP ; respecter les frontières décrites dans `ARCHITECTURE.md`.
- Éviter la duplication : rechercher les composants/services existants avant d’en créer un nouveau. Extraire uniquement les abstractions qui clarifient un motif réel.
- Ne pas ajouter de dépendance sans besoin identifié et revue de version/licence/maintenance.
- Configurer secrets et environnements hors du code ; aucune donnée personnelle dans logs ou fixtures commitées.
- Toute modification de schéma inclut une migration additive relue et une note sur compatibilité.
- Les fonctionnalités d’urgence doivent être explicites sur leur fiabilité et leur couverture ; ne jamais laisser croire qu’un écran ou numéro non connecté est un service opérationnel.
