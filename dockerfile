FROM amazoncorretto:21
WORKDIR /app
COPY /target/Lesson24-1.0-SNAPSHOT.jar lesson24.jar
ENTRYPOINT ["java", "-jar", "Lesson24-1.0-SNAPSHOT.jar"]
