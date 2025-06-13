FROM maven:3.9.9-eclipse-temurin-24 AS build

COPY . .
RUN mvn package

FROM azul/zulu-openjdk-alpine:24-jre-headless-latest

EXPOSE 8080

COPY --from=build target/blockchain-service*.jar blockchain-service.jar
ENTRYPOINT ["java","-jar","/blockchain-service.jar"]
