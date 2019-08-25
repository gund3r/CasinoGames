run:
	java -jar ./target/java-project-lvl1-1.0-SNAPSHOT-jar-with-dependencies.jar

build: clear update
	./mvnw verify

clear:
	./mvnw clean

update:
	./mvnw versions:update-properties versions:display-plugin-updates

.DEFAULT_GOAL := build-run
build-run: build run
