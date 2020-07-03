FROM openjdk:11-jdk-alpine
ADD build/libs/queue-server1.0.jar queue-server.jar
EXPOSE 61616
VOLUME /h2-data
ENTRYPOINT [ "java", "-jar", "queue-server.jar" ]