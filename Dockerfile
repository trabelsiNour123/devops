# Image de base légère avec Java 17
FROM openjdk:17-jdk-slim

# Créer un répertoire pour l'application
WORKDIR /app

# Copier le JAR généré par Maven (target/*.jar)
COPY target/*.jar app.jar

# Exposer le port (8080 par défaut pour Spring Boot)
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
