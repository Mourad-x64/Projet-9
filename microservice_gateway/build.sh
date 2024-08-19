./mvnw clean install -Dmaven.test.skip=true
docker stop microservice_gateway
docker rm microservice_gateway
docker build -t microservice_gateway:1.0 .
docker run -d -p 8080:8080/tcp --name microservice_gateway microservice_gateway:1.0
