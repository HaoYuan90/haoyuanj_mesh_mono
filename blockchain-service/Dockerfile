FROM azul/zulu-openjdk:24-latest

ARG JAR_FILE=target/blockchain-service*.jar
COPY ${JAR_FILE} blockchain-service.jar
ENTRYPOINT ["java","-jar","/blockchain-service.jar"]
