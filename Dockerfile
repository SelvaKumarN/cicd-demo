FROM maven:3.8.5-openjdk-17-slim as builder
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN mvn clean test package


FROM builder
COPY --from=builder /app/source/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]