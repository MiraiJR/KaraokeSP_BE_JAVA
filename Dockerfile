FROM eclipse-temurin:17-jdk-alpine

WORKDIR /backend

COPY mvnw .
COPY .mvn .mvn
COPY . ./

RUN ./mvnw clean package

COPY ./target .

EXPOSE 3000

CMD ["java", "-jar", "/backend/target/karaoke-0.0.1-SNAPSHOT.jar"]
