# Backend SOS — préparation

Ce répertoire réserve le module Java/Spring Boot demandé. Il n’est volontairement pas encore un projet compilable : les versions Java/Spring, l’outil de migration et les environnements doivent être confirmés. Ne pas inventer d’endpoints ou données métier.

La structure cible et les responsabilités sont décrites dans [`../docs/ARCHITECTURE.md`](../docs/ARCHITECTURE.md), les conventions HTTP dans [`../docs/API.md`](../docs/API.md), et les décisions en attente dans [`../docs/DECISIONS.md`](../docs/DECISIONS.md).

Structure recommandée lors de l’initialisation : `src/main/java/com/sos/` (config, security, common et packages par fonctionnalité), `src/main/resources/` (configuration non secrète), `src/test/` (tests). Les migrations versionnées vivront dans `database/migrations/` si l’outil retenu les prend en charge.
