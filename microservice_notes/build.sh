./mvnw clean install -Dmaven.test.skip=true
docker stop microservice_note
docker rm microservice_note
docker build -t microservice_note:1.0 .
docker run -d -p 8083:8083/tcp --name microservice_note microservice_note:1.0