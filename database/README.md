# PostgreSQL et migrations

Les migrations SQL Flyway versionnées se trouvent dans `../backend/src/main/resources/db/migration/` afin d'être embarquées dans le JAR. La migration V1 s'assure uniquement que le schéma `sos` existe ; elle ne crée aucune table métier ni donnée. Voir [`../docs/DATABASE.md`](../docs/DATABASE.md) avant d'ajouter une migration.
