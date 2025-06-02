# Makefile to help build and dockerize services for local development ONLY

.PHONY: package-blockchain
package-blockchain:
	mvn package -f ./blockchain-service/pom.xml

.PHONY: docker-blockchain
docker-blockchain:
	docker build -t blockchain-service-local ./blockchain-service

.PHONY: clean-blockchain
clean-blockchain:
	rm -rf ./*/target

.PHONY: docker-chat
docker-chat:
	docker build -t chat-service-local ./chat-service

.PHONY: clean-chat
clean-chat:
	rm -rf ./*/target