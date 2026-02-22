build:
	cd app && ./gradlew build

run-dist:
	cd app && ./gradlew installDist
	cd app && ./build/install/app/bin/app

.PHONY: build run-dist