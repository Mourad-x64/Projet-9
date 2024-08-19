./mvnw clean install -Dmaven.test.skip=true
docker stop microservice_patient
docker rm microservice_patient
docker build -t microservice_patient:1.0 .
docker run -d -p 8081:8081/tcp --name microservice_patient microservice_patient:1.0