# Base de données

## Moteur et propriété

PostgreSQL est la base du backend. Seul le backend y accède. Le schéma est versionné dans `database/migrations/` et appliqué de manière incrémentale par un outil de migration choisi avant le premier schéma (proposition : Flyway, à valider). Le démarrage du backend ne doit pas créer silencieusement un schéma de production via Hibernate.

## Conventions

- Schéma et objets en `snake_case`, tables au pluriel ; clés primaires nommées `id`.
- Dates de création/modification en UTC (`timestamptz`) quand elles sont nécessaires.
- Contraintes d’intégrité explicites : `NOT NULL`, `UNIQUE`, clés étrangères et index justifiés par les usages.
- Pas de données réelles ou de données de démonstration dans les migrations de structure.
- Données de test synthétiques, clairement marquées, dans des fixtures propres aux tests uniquement.
- Les migrations déjà partagées ne sont pas réécrites : une nouvelle migration corrige le schéma.

## Modèle métier

Aucune table métier n’est fixée à ce stade. Les entités, relations, règles de conservation, suppression, confidentialité et besoins de recherche doivent être validés à partir des cas d’usage avant la première migration. Ne pas déduire qu’un compte utilisateur, un signalement ou une ressource possède un modèle définitif sans cette revue.

## Environnements et sauvegardes

Développement, test et production utilisent des bases distinctes et des identifiants distincts. Les secrets sont injectés par variables d’environnement ou gestionnaire de secrets. Définir stratégie de sauvegarde/restauration, rétention et migrations de retour arrière avant toute mise en production.
