FROM eclipse-temurin:22.0.1_8-jre-jammy
WORKDIR /app
COPY target/projet9MicroservicePatient-1.0.jar .
CMD ["java", "-Dusername=root", "-Dpassword=root", "-Durl=jdbc:mysql://host.docker.internal:3306/microservicePatient", "-Dspring.profiles.active=docker", "-jar", "projet9MicroservicePatient-1.0.jar"]
EXPOSE 8081