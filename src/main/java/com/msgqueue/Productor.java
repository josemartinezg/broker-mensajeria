package com.msgqueue;

import com.google.gson.Gson;
import com.msgqueue.Models.Lectura;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static spark.Spark.get;

public class Productor {
    public Productor() {
    }
    static Gson gson = new Gson();
    static Random random;
    static Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void enviarMensaje(String cola) throws JMSException {
        /*TODO: Tomar en cuenta la implementación del failover*/
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("failover:tcp://localhost:61616");
        javax.jms.Connection connection = factory.createConnection("admin", "admin");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(cola);
        // Creando el objeto de referencia para enviar los mensajes.
        MessageProducer producer = session.createProducer(queue);

        while(true){
            try{
                TimeUnit.SECONDS.sleep(2);
                random = new Random();
                int id = random.nextInt(1 + 1)  + 1;
                String mensajeDispositivo = nuevaLectura(id);
                TextMessage message = session.createTextMessage(mensajeDispositivo);
                producer.send(message);
                System.out.println("[X] Enviado: '" + mensajeDispositivo + "'");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static String nuevaLectura(int idDispositivo) {
        String dateSrt = format.format(new Date());
        double tempreratura = generateRandom(100, 't');
        double humedad = generateRandom(100, 'h');
        Lectura lectura = new Lectura(dateSrt, idDispositivo, tempreratura, humedad);
        return gson.toJson(lectura);
    }
    /*Simulación de sensores de temperatura y humedad.*/
    public static double generateRandom(int bound, char var) {
        random = new Random();
        switch (var) {
            case 't':
                return random.nextInt((bound - (-bound)) + 1) + (-bound);
            case 'h':
                return random.nextInt(bound) +1;
        }
        return random.nextInt(10);
    }
}
