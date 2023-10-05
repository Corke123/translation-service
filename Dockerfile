FROM gradle:8.2.1-jdk17-focal AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts /app/
COPY gradle /app/gradle
COPY src /app/src

RUN gradle clean build --no-daemon

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/application.jar

ENV RESOURCE_SERVER_URL=placeholder

EXPOSE 8080

CMD ["java", "-jar", "application.jar"]
