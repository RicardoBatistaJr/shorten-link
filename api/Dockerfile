
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/link-shorten-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9292

CMD ["java", "-jar", "app.jar"]
