# Etapa de construcción
FROM maven:3.6.3-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa de empaquetado
FROM openjdk:8-alpine
WORKDIR /app
COPY --from=build /app/target/challenge-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "challenge-0.0.1-SNAPSHOT.jar"]
