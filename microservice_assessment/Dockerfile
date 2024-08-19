FROM eclipse-temurin:22.0.1_8-jre-jammy
WORKDIR /app
COPY target/projet9microserviceassessment-1.0.jar .
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "projet9microserviceassessment-1.0.jar"]
EXPOSE 8084