FROM eclipse-temurin:22.0.1_8-jre-jammy
WORKDIR /app
COPY target/microservicenotes-0.0.1-SNAPSHOT.jar .
CMD ["java", "-Dhost=host.docker.internal", "-Dport=27017", "-Dusername=admin", "-Dpassword=admin", "-Dspring.profiles.active=docker", "-jar", "microservicenotes-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083