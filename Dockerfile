FROM openjdk:8
ADD target/docker-empire.jar docker-empire.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-empire.jar"]