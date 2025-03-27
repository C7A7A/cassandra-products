FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /workspace

COPY gradlew .
COPY settings.gradle .
COPY gradle gradle

RUN mkdir -p backend/src

COPY backend/build.gradle backend
COPY backend/src backend/src

RUN chmod +x gradlew
RUN ./gradlew -p backend clean build -x test


FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=builder /workspace/backend/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

