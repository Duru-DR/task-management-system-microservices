FROM openjdk:21-jdk-slim

RUN useradd -ms /bin/bash appuser
WORKDIR /app
COPY target/notification-0.0.1-SNAPSHOT.jar app.jar
RUN chown -R appuser:appuser /app
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
