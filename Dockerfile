FROM openjdk:8-alpine

COPY . .

# RUN ./gradlew clean build

WORKDIR /build/libs

#RUN javac Main.java

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "CRUDCliente-0.0.1-SNAPSHOT.jar"]