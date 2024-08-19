FROM eclipse-temurin:22.0.1_8-jre-jammy
WORKDIR /app
COPY target/projet9microservicefront-1.0.jar .
CMD ["java", "-Dspring.profiles.active=docker", "-jar", "projet9microservicefront-1.0.jar"]
EXPOSE 8082