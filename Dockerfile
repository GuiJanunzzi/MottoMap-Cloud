FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /build

COPY MottoMap-JavaBackend/ ./MottoMap-JavaBackend/
WORKDIR /build/MottoMap-JavaBackend
 
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ENV ACTIVE_PROFILE=prod

COPY --from=build /build/MottoMap-JavaBackend/target/*.jar app.jar

RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
 
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]