FROM maven:3.9.3-eclipse-temurin-17

RUN apt-get update && \
    apt-get install -y git curl && \
    curl -fsSL https://get.docker.com -o get-docker.sh && sh get-docker.sh && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /home/jenkins/agent