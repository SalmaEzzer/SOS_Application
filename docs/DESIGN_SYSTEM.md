# Design system Android

## Direction

La cible est Jetpack Compose avec Material 3. Le dépôt actuel contient encore une activité AppCompat et des ressources XML ; elles sont conservées jusqu’à une migration écran par écran. Aucun changement visuel n’est présumé à partir du seul nom SOS.

## Règles

- Source unique pour le thème partagé dans `ui/theme/` : palette, typographie, formes et thèmes clair/sombre.
- Composants partagés seulement lorsqu’un motif est réutilisé et possède un comportement/style cohérent ; les composants spécifiques restent dans leur feature.
- Utiliser les composants Material 3 et leurs états accessibles avant de créer un composant maison.
- Les écrans décrivent des états explicites : chargement, contenu, vide, erreur et action de reprise adaptée.
- Respecter tailles de texte système, contraste, TalkBack, cibles tactiles et localisation. Les couleurs ne sont pas l’unique signal d’état.
- Les valeurs métier réelles affichées doivent venir d’une source approuvée ; les maquettes et fixtures sont étiquetées comme exemples.

## Validation visuelle

Avant adoption d’une palette ou d’un langage visuel, valider avec l’équipe : couleurs, logo, typographie, densité, langues supportées et comportement hors connexion. Ne pas déduire l’identité graphique du dépôt Android généré.
