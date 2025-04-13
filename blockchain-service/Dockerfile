FROM azul/zulu-openjdk-alpine:24-jre-headless-latest

ARG JAR_FILE=target/blockchain-service*.jar
COPY ${JAR_FILE} blockchain-service.jar
ENTRYPOINT ["java","-jar","/blockchain-service.jar"]
