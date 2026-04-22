# ──────────────────────────────────────────────────────────────
# Stage 1 - Build: compila el JAR con Maven
# ──────────────────────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar pom.xml primero para aprovechar cache de capas de Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar
COPY src ./src
RUN mvn package -DskipTests -B

# ──────────────────────────────────────────────────────────────
# Stage 2 - Runtime: imagen mínima y segura (sin shell)
# ──────────────────────────────────────────────────────────────
FROM gcr.io/distroless/java21-debian12

WORKDIR /app

# Copiar solo el JAR generado desde el stage de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
