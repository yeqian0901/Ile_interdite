# Ile_interdite Projet POGL GROUP2 INFO L2 
Yeqian HE et Jincheng YE

(notice: Les routes de dossier pour les images sont peut-etre differents dans les differents ordinateurs. Merci pour votre comprehension!)

#Rapport Projet

Objectif : creer le jeu d'Ile d'interdite

ConditionGagne : tous les joueurs partissent de helicopter avec posseder tous le cles apres presser le button "fin de tour" pour afficher le graphe gagné.

ConditionFail : le heliport est submergé avant de gagner ou les cases specials d'un element sont tous submergé mais il n'y a pas assis des cles de cet element pour le somme de tous les joueur.

Class:

1.Case : definir chaque partie de "zone" ce qui inclure "element","coordonnee","inondation".

2.Joueur : definir le player qui participe ce jeu, inclure "coordonnee","cles".

3.Graphe : definir le grille de jeu et mettre les cases, inclure "l'ensemble de case","la taille de grille".

4.Game : Dessiner les contenues dans Graphe, Joueur et ajouter les actions specials, inclure "Fenetre de jeu", "Boutton de chaque action".

Vues :
1. Afficher les zones de l’île et leur état (class Game)
2. Un bouton « fin de tour » qui peut procéder à l’inondation de trois zones (non encore submergées ) aléatoires.(class Game -> start())
3. Le modèle avec un joueur (class Joueur)
4. La vue représente le joueur sur sa zone (class Game -> draw())
5. Étendre la vue et le contrôleur avec des moyens d’ordonner le mouvement du joueur. On choisit clics de boutons (class Game -> direction())
6. Étendre le contrôleur pour que le joueur puisse réaliser au maximum trois actions avant de cliquer sur le bouton « fin de tour ». (class Game -> direction(), on définit « click » pour limiter les étapes.)
7. Étendre l’ensemble pour permettre plusieurs joueurs. Le contrôleur doit faire agir les joueurs à tour de rôle, en insérant une phase d’inondation (« fin de tour ») à la fin du tour de chaque joueur. (class Game -> Joueur[] permet d’ajouter plusieurs joueurs)
8. Étendre l’ensemble pour ajouter aux coups du joueur l’action d’assécher la zone sur laquelle il se trouve ou une zone adjacente, en choisissant une interface utilisateur cohérente avec celle permettant le déplacement.(class Game -> direction(), Button « assécher »)
9. La vue indique le joueur dont c’est le tour et le nombre d’actions auxquelles il a encore droit. (class Game -> direction(), on utilise « label » pour stocker)
10. Étendre le modèle pour inclure les zones spéciales, les clés que peut posséder un joueur, et l’action de récupérer un artefact. Étendre l’effet du bouton «fin de tour» pour que le joueur dont c’était le tour reçoive, aléatoirement, une cléou rien. (class Game -> getCle() pour obtenir cle dans le zone spécial)
11. La vue d’un panneau représentant les joueurs, et y indiquer pour chacun les clés et artefacts qu’il possède. (class Game -> cle(), affiche les clés possède de chaque joueur.)
12. Étendre le contrôleur pour inclure l’action de récupérer un artefact et la fin de partie gagnante. (class Game -> isWinning())
13. Les critères caractérisant les parties perdues et arrêter la partie lorsqu’ils se réalisent. (class Game -> isFail())

Difficultés :
1.	Penser l’ordre de programmation des différents éléments.
2.	Visualisation des cartes et des joueurs.
3.	Deplacer les joueurs partager.
4.	Renouveler l’image chaque tour.
5.	File de images est toujours incorrect.
