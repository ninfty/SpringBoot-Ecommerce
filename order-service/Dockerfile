# Stage 1: Build with Maven
FROM maven:3.9-eclipse-temurin-24 AS build

WORKDIR /build

COPY . .

RUN mvn package -DskipTests

# Stage 2: Runtime with JDK 24
FROM openjdk:24

WORKDIR /app

# Copy the built JAR file
COPY --from=build /build/target/*.jar order.jar

ENTRYPOINT ["java", "-jar", "order.jar"]