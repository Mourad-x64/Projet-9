./mvnw clean install -Dmaven.test.skip=true
docker stop microservice_assessment
docker rm microservice_assessment
docker build -t microservice_assessment:1.0 .
docker run -d -p 8084:8084/tcp --name microservice_assessment microservice_assessment:1.0