Mandatos para crear el .jar:
   ./gradlew; ./gradlew tasks;  ./gradlew shadowJar;
Para probar jar:
   java -jar build/libs/sensor-cliente.jar

Cambiar el build.gradle para la generación del 2do jar.
   ./gradlew; ./gradlew tasks; ./gradlew shadowJar;
Para probar jar:
      java -jar build/libs/queue-server.jar


***Recordar que el build dependerá del dockerfile.
sudo docker build -t sensor-queue-server .;sudo docker run --rm -p 61616:61616 sensor-queue-server

sudo docker build -t sensor-web-client .; sudo docker run --rm -p 4567:4567 sensor-web-client



sudo docker tag sensor-queue-server josemartinezg/sensor-queue-server; sudo docker push josemartinezg/sensor-queue-server; sudo docker tag sensor-web-client josemartinezg/sensor-web-client; sudo docker push josemartinezg/sensor-web-client; sudo docker-compsoe up
