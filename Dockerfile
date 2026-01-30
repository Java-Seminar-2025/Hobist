# Stage 1: Build sa Maven + Java 25
FROM maven:3.9-eclipse-temurin-25 AS builder

# Koristiš alpine ili noble? Ovdje koristim standardnu (brža, ali možeš promijeniti na -alpine ako želiš manji build image)
WORKDIR /build

# Prvo kopiraj pom.xml + dependency:go-offline za caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Kopiraj source i build JAR
COPY src ./src
RUN mvn clean package -DskipTests   # -DskipTests za brži build; makni ako želiš testove na Renderu

# Stage 2: Lagana runtime slika samo sa JRE 25
FROM eclipse-temurin:25-jre

WORKDIR /app

# Kopiraj samo finalni JAR iz build stage-a
COPY --from=builder /build/target/*.jar app.jar

# Spring Boot default port je 8080, Render ga interno mapira na $PORT (ali obično radi i sa 8080)
EXPOSE 8080

# Pokreni app (možeš dodati -XX opcije za memoriju ako treba)
ENTRYPOINT ["java", "-jar", "app.jar"]