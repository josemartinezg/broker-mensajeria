FROM openjdk:11
ADD build/libs/sensor-cliente.jar sensor-cliente.jar
EXPOSE 4567
VOLUME /tmp
ENTRYPOINT [ "java", "-jar", "sensor-cliente.jar" ]