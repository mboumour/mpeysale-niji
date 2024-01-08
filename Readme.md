# Projet Cocktail App

## Étudiants
- [Mouad BOUMOUR](https://github.com/mboumour)
- [Anas NAAMI](https://github.com/Anasnaami)

## Fonctionnalités développées
Features minimales:
- Tab Bar
    - Premier onglet : Recherche
    - Deuxième onglet : liste des catégories
    - Troisième onglet : liste des ingrédients
- Liste des recettes associées aux catégories/recettes
- Détail de recette


## Difficultés rencontrées
Pendant le processus de développement de cette application, plusieurs difficultés ont été rencontrées :

1. Débogage des erreurs via le Logcat
Comprendre et résoudre les erreurs rencontrées pendant le développement a été un défi majeur. L'utilisation du Logcat pour le suivi et le débogage des erreurs a été essentielle pour identifier et corriger les problèmes rencontrés dans le code.

2. Affichage d'images à partir d'une URL
L'affichage d'images à partir d'URLs spécifiques a été une difficulté. Pour gérer cet aspect, nous avons opté pour l'utilisation de bibliothèques tierces telles que Glide plutôt que Picasso pour charger les images. Cela nous a permis d'améliorer la gestion et le chargement des images dans notre application.

3. Traitement des données JSON reçues
Les fichiers JSON fournis par l'API ne suivaient pas toujours une structure claire et uniforme. Par exemple, lors de la récupération des détails d'une recette, les champs étaient définis comme strIngredient$i et strMeasure$i, avec i variant de 1 à 15, et parfois avec des valeurs nulles. Cette organisation des données a nécessité un traitement spécifique pour récupérer et afficher correctement les informations, ce qui a rendu la logique de récupération des données plus complexe.

4. Gestion des cas d'erreur de l'API
La gestion des erreurs provenant de l'API a été une préoccupation importante. Nous avons dû mettre en place des mécanismes pour gérer les cas où les données ne sont pas récupérées correctement de l'API. Cela a nécessité la mise en place de logiques de gestion des erreurs pour éviter les bugs et assurer une meilleure expérience utilisateur.

5. Réduction de la duplication de code
La réduction de la duplication de code a été une préoccupation, mais rendre le code plus générique a parfois rendu le développement plus difficile. L'objectif était d'éviter les répétitions inutiles tout en conservant la lisibilité et la maintenabilité du code.
