# Makefile to help build and dockerize services for local development ONLY

.PHONY: package
package:
	mvn package -f ./blockchain-service/pom.xml

.PHONY: docker
docker:
	docker build -t blockchain-service-local ./blockchain-service

.PHONY: all
all: package docker

.PHONY: clean
clean:
	rm -rf ./*/target