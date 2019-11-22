# Projet Long 2018 - 2019

M1 informatique - Université Paris Diderot Paris 7

## Definition

Ce projet est un suiveur de ligne Lejos 0.9.1 NXT qui grace a un algorithme de balayage il arrive a suivre une ligne de couleur choisie au préalable.
Notre robot est certe un peu lent, mais arrive a suivre une ligne aussi compliqué soit elle, que ce soit des courbes très serrés ou des formes géometrique, le robot s'adapte et essai de retrouver sont chemin en élargissant son ongle de rotation au fur et a mesure. 

## Git Structure
Notre git est divisé en 2 répertoire :

* Sandbox : Ici il y a nos fichiers test, c'est le répertoire ou on a développer le projet.
* Production : Ici, une fois qu'on termine une tache et qu'on fini de tester et que ça marche sur le répertoire "SandBox" on l'ajoute sur ce répertoire qui est concideré comme le dépot de notre version final.



### Installing
Pour installer notre projet sur la brique suivez les étapes suivantes:


Soit utilisé notre script qui se trouve dans Production/buildSend.sh :

```
./buildSend
```

Soit utilisé les commandes suivantes dans l'ordre:

```
nxjc Core.java
```
```
nxj -r -o Core.nxj Core
```


## Authors

* **Aouessar Oussama** - *Master*
* **Charif Mohammed-Achraf** - *Master*

## License

This project is licensed under MIT

## Acknowledgements

Merci a nos enseignants :

* Giovanni Bernardi
* Léo Planche
* Hadrien Batmalle
