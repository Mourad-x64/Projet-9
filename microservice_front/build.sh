./mvnw clean install -Dmaven.test.skip=true
docker stop microservice_front
docker rm microservice_front
docker build -t microservice_front:1.0 .
docker run -d -p 8082:8082/tcp --name microservice_front microservice_front:1.0