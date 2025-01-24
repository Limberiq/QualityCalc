FROM eclipse-temurin:17-jdk as builder
WORKDIR /opt/app
COPY pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:17-jre
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
