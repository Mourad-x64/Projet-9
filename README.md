
![](images/16915753889047_fr_DA-JAVA_P7-P8-P9_P9-02 (1) Large.jpeg)

# Médialbo Solutions
Projet 9 java OpenClassRooms, le but de ce projet est de créer une application de gestion des patients avec la prise en compte 
des notes du médecin et un diagnostic des risques de diabète en fonction des notes.
Le microservice de gestion des patients utilise une base de donnée MySQL et le microservice de gestion des notes 
une base de données MongoDB.
Ce projet doit s'architechturer en microservices dockerisés, les microservices sont géré par un microservice Spring Cloud Gateway.

### frontend
le microservice front à été réalisé avec thymeleaf pour reprendre les connaissances acquises dans les précédents projets.

### environnement

    JDK 22
    Maven 3.9.7
    MySQL 8.0.31
    MongoDB 6.0.6

### sécurité

on utilise l'authentification par défaut de spring security pour authentifier l'utilisateur à l'application.

prérequis : 
    spring security 6.2.4
    spring webflux 3.2.5


### configuration des bases de données

pour la base de donnée Mysql et Mongodb nous utilisons des variables d'environnement pour la connection.

### installation

- pour installer le projet clonnez le repo suivant : https://github.com/Mourad-x64/Projet9
- ajoutez les variables d'environnement en vérifiant les fichiers .properties
- démmarrez les microservices
- accédez à : localhost:8080/ui/patient/

### installation des microservices docker

- lancez les fichiers build.sh dans chaque microservice
- lancez la commande : "docker compose up" au même endroit que le fichier docker-compose.yml 
- accédez à : localhost:8080/ui/patient/