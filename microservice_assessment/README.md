# commandes docker :
- mvn install -Dmaven.test.skip=true -> commande pour générer le jar dans maven
```
  docker stop microservice_assessment
  docker rm microservice_assessment
  docker build -t microservice_assessment:1.0 .
  docker run -d -p 8084:8084/tcp --name microservice_assessment microservice_assessment:1.0
```
- docker rmi microservice_patient supprimer l'image
- docker image list -> lister les images
- docker run -d -p 8084:8084/tcp --name microservice_assessment microservice_assessment:1.0
- docker ps -a -> lister les conteneurs
- docker logs microservice_patient -> afficher les logs
- docker rm microservice_patient -> supprimer un conteneur
- docker system prune -f -> nettoyage des images
