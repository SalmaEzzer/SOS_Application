# Base de données

## Moteur et propriété

PostgreSQL est la base du backend. Seul le backend y accède. Les migrations Flyway exécutables sont versionnées dans `backend/src/main/resources/db/migration/` et appliquées de manière incrémentale. Le répertoire racine `database/` conserve les conventions et la documentation du modèle. Le démarrage du backend ne doit pas créer silencieusement un schéma de production via Hibernate.

## Conventions

- Schéma et objets en `snake_case`, tables au pluriel ; clés primaires nommées `id`.
- Dates de création/modification en UTC (`timestamptz`) quand elles sont nécessaires.
- Contraintes d’intégrité explicites : `NOT NULL`, `UNIQUE`, clés étrangères et index justifiés par les usages.
- Pas de données réelles ou de données de démonstration dans les migrations de structure.
- Données de test synthétiques, clairement marquées, dans des fixtures propres aux tests uniquement.
- Les migrations déjà partagées ne sont pas réécrites : une nouvelle migration corrige le schéma.

## Modèle métier

Aucune table métier n’est fixée à ce stade. Au démarrage, Flyway crée seulement le schéma PostgreSQL `sos` et sa table technique d'historique ; la migration V1 ne fait que s'assurer que le schéma existe. Les entités, relations, règles de conservation, suppression, confidentialité et besoins de recherche doivent être validés à partir des cas d’usage avant toute migration métier. Ne pas déduire qu’un compte utilisateur, un signalement ou une ressource possède un modèle définitif sans cette revue.

## Environnements et sauvegardes

Développement, test et production utilisent des bases distinctes et des identifiants distincts. Les secrets sont injectés par variables d’environnement ou gestionnaire de secrets. Définir stratégie de sauvegarde/restauration, rétention et migrations de retour arrière avant toute mise en production.
