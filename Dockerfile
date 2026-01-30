# Stage 1: Build the app with Maven
FROM maven:3.9.9-eclipse-temurin-25 AS builder
WORKDIR /build

# Copy pom first for caching dependencies
COPY pom.xml .
# Download dependencies (cache this layer)
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests  # Skip tests for faster builds; remove flag if you want tests

# Stage 2: Lightweight runtime image
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /build/target/*.jar app.jar

# Expose your app's port (default for Spring Boot)
EXPOSE 8080

# Run the app (use -Dspring.profiles.active=prod if needed)
ENTRYPOINT ["java", "-jar", "app.jar"]