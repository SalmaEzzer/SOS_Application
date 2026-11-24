# Contrat API REST

Ce document fixe les conventions, pas un inventaire de fonctionnalités déjà implémentées. Les routes métier seront ajoutées après validation des cas d’usage et du modèle de données.

## Conventions

- Préfixe `/api/v1`; ressources au pluriel et noms en minuscules avec tirets si nécessaire.
- JSON UTF-8. Les dates sont ISO 8601 avec fuseau explicite ; les identifiants sont opaques côté client.
- Codes HTTP : `200` lecture/mise à jour, `201` création, `204` suppression sans corps, `400` entrée invalide, `401` authentification absente/invalide, `403` accès interdit, `404` ressource absente, `409` conflit métier, `500` erreur inattendue.
- Validation d’entrée côté serveur ; réponses d’erreur sans trace technique, secret ni donnée sensible.
- Pagination et tri sont normalisés avant les endpoints de listes ; documenter les limites et valeurs par défaut dans chaque contrat.

## Erreur stable

```json
{
  "type": "about:blank",
  "title": "Requête invalide",
  "status": 400,
  "detail": "Une ou plusieurs valeurs sont invalides.",
  "instance": "/api/v1/...",
  "code": "VALIDATION_ERROR",
  "fieldErrors": []
}
```

Le champ `detail` reste générique pour les erreurs sensibles ; `fieldErrors` peut identifier les champs invalides sans recopier leur valeur.

## JWT

Les endpoints protégés attendront `Authorization: Bearer <token>`. Les endpoints publics et exigences de rôle seront documentés par route. Le flux d’émission/renouvellement, durée, rotation, révocation, stockage Android et protection contre l’énumération doivent être validés avant de définir les routes d’authentification. Ne jamais écrire de jeton dans les logs.

## Contrat et compatibilité

Documenter les endpoints avec OpenAPI quand ils seront créés. Les changements incompatibles nécessitent une nouvelle version ou un plan de migration annoncé. Les DTO HTTP restent distincts des entités JPA et des modèles de domaine.
