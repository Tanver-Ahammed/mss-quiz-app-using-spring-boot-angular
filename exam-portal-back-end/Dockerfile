# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS quiz_builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal JRE image
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=quiz_builder /app/target/exam-portal-back-end-0.0.1-SNAPSHOT.jar mss-quiz-app.jar

EXPOSE 8080

# Specify the default command to run on startup
CMD ["java", "-jar", "mss-quiz-app.jar"]