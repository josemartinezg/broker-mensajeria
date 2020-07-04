FROM openjdk:11
ADD build/libs/sensor-cliente.jar queue-server.jar
EXPOSE 61616
VOLUME /tmp
ENTRYPOINT [ "java", "-jar", "queue-server.jar" ]