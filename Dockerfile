#FROM eclipse-temurin:11.0.16_8-jdk-alpine
FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]